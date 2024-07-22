'use strict';

// username-page, chat-page, usernameForm, messageForm, messageInput, connecting 요소를 DOM에서 가져옵니다.
const usernamePage = document.querySelector('#username-page');
const chatPage = document.querySelector('#chat-page');
const usernameForm = document.querySelector('#usernameForm');
const messageForm = document.querySelector('#messageForm');
const messageInput = document.querySelector('#message');
const connectingElement = document.querySelector('.connecting');
const chatArea = document.querySelector('#chat-messages');
const logout = document.querySelector('#logout');

let stompClient = null;  // STOMP 클라이언트 변수
let nickname = null;  // 사용자의 닉네임
let fullname = null;  // 사용자의 전체 이름
let selectedUserId = null;  // 선택된 사용자 ID

// 서버에 연결하는 함수
function connect(event) {
    nickname = document.querySelector('#nickname').value.trim();  // 닉네임 입력값 가져오기
    fullname = document.querySelector('#fullname').value.trim();  // 전체 이름 입력값 가져오기

    if (nickname && fullname) {
        usernamePage.classList.add('hidden');  // 닉네임 페이지 숨기기
        chatPage.classList.remove('hidden');  // 채팅 페이지 보이기


        const socket = new SockJS('/ws');  // SockJS 객체 생성 (/ws 위치는 WebSocketConfig에서 설정)
        stompClient = Stomp.over(socket);  // STOMP 클라이언트 생성

        stompClient.connect({}, onConnected, onError);  // 서버에 연결 ex) stompClient.connect(headers, connectCallback, errorCallback);
    }
    event.preventDefault();
}

// 연결 성공 시 호출되는 함수
function onConnected() {
    // 사용자의 메시지를 받을 구독
    stompClient.subscribe(`/user/${nickname}/queue/messages`, onMessageReceived);
    // 공용 메시지를 받을 구독
    stompClient.subscribe(`/user/public`, onMessageReceived);

    // 사용자 등록
    stompClient.send("/app/user.addUser",
        {},
        JSON.stringify({nickName: nickname, fullName: fullname, status: 'ONLINE'})
    );
    document.querySelector('#connected-user-fullname').textContent = fullname;  // 연결된 사용자 이름 표시
    findAndDisplayConnectedUsers().then();  // 연결된 사용자 목록 가져오기
}

// 연결 실패 시 호출되는 함수
function onError() {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';  // 오류 메시지 표시
}

// 메시지 수신 시 호출되는 함수
async function onMessageReceived(payload) {
    // 비동기 함수 호출 및 완료될 때까지 기다림
    await findAndDisplayConnectedUsers();  // 연결된 사용자 목록 갱신
    console.log('Message received', payload);
    const message = JSON.parse(payload.body);
    // 선택된 사용자 ID가 있고, 메시지의 보낸 사람 ID와 일치하면 메시지를 표시
    if (selectedUserId && selectedUserId === message.senderId) {
        displayMessage(message.senderId, message.content);  // 메시지 표시
        chatArea.scrollTop = chatArea.scrollHeight;  // 스크롤 하단으로 이동
    }
    // 선택된 사용자가 있으면 해당 항목을 활성화 표시
    if (selectedUserId) {
        document.querySelector(`#${selectedUserId}`).classList.add('active');  // 활성화된 사용자 항목 표시
    } else {
        messageForm.classList.add('hidden');  // 메시지 폼 숨기기
    }

    const notifiedUser = document.querySelector(`#${message.senderId}`);
    if (notifiedUser && !notifiedUser.classList.contains('active')) {
        const nbrMsg = notifiedUser.querySelector('.nbr-msg');
        nbrMsg.classList.remove('hidden');  // 메시지 수 표시
        nbrMsg.textContent = '';  // 메시지 수 초기화
    }
}

// 연결된 사용자 목록을 가져와 표시하는 함수
async function findAndDisplayConnectedUsers() {
    // fetch는 네트워크 요청을 보냄. '/users' URL에 GET 요청. fetch는 기본적으로 비동기 함수로, Promise를 반환
    const connectedUsersResponse = await fetch('/users');  // 사용자 목록 요청
    let connectedUsers = await connectedUsersResponse.json();  // 응답 JSON으로 변환
    connectedUsers = connectedUsers.filter(user => user.nickName !== nickname);  // 현재 사용자는 제외
    const connectedUsersList = document.getElementById('connectedUsers'); // 연결된 사용자 목록을 표시할 HTML 요소를 가져옴
    connectedUsersList.innerHTML = '';  // 목록 초기화

    connectedUsers.forEach(user => { // 사용자 목록 표시
        appendUserElement(user, connectedUsersList);  // 각 사용자에 대해 appendUserElement를 호출하여 사용자 요소를 목록에 추가합니다.
        if (connectedUsers.indexOf(user) < connectedUsers.length - 1) { // 현재 요소가 마지막 요소가 아닌 경우
            const separator = document.createElement('li');
            separator.classList.add('separator');
            connectedUsersList.appendChild(separator);  // 구분자 추가
        }
    });
}

// 사용자 요소를 목록에 추가하는 함수
function appendUserElement(user, connectedUsersList) {
    const listItem = document.createElement('li');
    listItem.classList.add('user-item');
    listItem.id = user.nickName;

    const userImage = document.createElement('img');
    userImage.src = '../img/user_icon.png';
    userImage.alt = user.fullName;

    const usernameSpan = document.createElement('span');
    usernameSpan.textContent = user.fullName;

    const receivedMsgs = document.createElement('span');
    receivedMsgs.textContent = '0';
    receivedMsgs.classList.add('nbr-msg', 'hidden');

    listItem.appendChild(userImage);
    listItem.appendChild(usernameSpan);
    listItem.appendChild(receivedMsgs);

    listItem.addEventListener('click', userItemClick);  // 클릭 이벤트 추가

    connectedUsersList.appendChild(listItem);
}

// 사용자 목록 항목 클릭 시 호출되는 함수
function userItemClick(event) {
    // 모든 사용자 목록 항목에서 'active' 클래스를 제거하여 비활성화 상태로 만듦
    document.querySelectorAll('.user-item').forEach(item => {
        item.classList.remove('active');
    });

    // 메시지 폼을 보이도록 설정하여 사용자가 메시지를 입력할 수 있게 함
    messageForm.classList.remove('hidden');

    // 클릭된 사용자 항목을 가져옴
    const clickedUser = event.currentTarget;
    // 클릭된 사용자 항목에 'active' 클래스를 추가하여 활성화 상태로 만듦
    clickedUser.classList.add('active');

    // 클릭된 사용자의 ID를 가져와 'selectedUserId' 변수에 저장
    selectedUserId = clickedUser.getAttribute('id');

    // 선택된 사용자의 채팅 내용을 가져와 표시
    fetchAndDisplayUserChat().then();

    // 클릭된 사용자 항목에서 메시지 수를 표시하는 요소를 가져옴
    const nbrMsg = clickedUser.querySelector('.nbr-msg');
    // 메시지 수 표시 요소를 숨김
    nbrMsg.classList.add('hidden');
    // 메시지 수 초기화
    nbrMsg.textContent = '0';
}

// 사용자 채팅 내용을 가져와 표시하는 함수
async function fetchAndDisplayUserChat() {
    // 선택된 사용자와 현재 사용자 간의 채팅 내용을 서버에서 가져옴
    const userChatResponse = await fetch(`/messages/${nickname}/${selectedUserId}`);
    // 서버에서 받은 응답을 JSON 형태로 변환
    const userChat = await userChatResponse.json();
    // 채팅 영역을 초기화하여 기존 메시지를 모두 제거
    chatArea.innerHTML = '';
    // 채팅 내용을 하나씩 화면에 표시
    userChat.forEach(chat => {
        displayMessage(chat.senderId, chat.content);
    });
    // 채팅 영역의 스크롤을 맨 아래로 이동하여 최신 메시지가 보이도록 함
    chatArea.scrollTop = chatArea.scrollHeight;
}

// 메시지를 화면에 표시하는 함수
function displayMessage(senderId, content) {
    // 새로운 메시지를 담을 div 요소를 생성
    const messageContainer = document.createElement('div');
    // 메시지에 공통적으로 적용될 클래스 추가
    messageContainer.classList.add('message');

    // 메시지의 발신자가 현재 사용자일 경우
    if (senderId === nickname) {
        // 발신자 메시지 스타일을 적용할 클래스 추가
        messageContainer.classList.add('sender');
    } else {
        // 수신자 메시지 스타일을 적용할 클래스 추가
        messageContainer.classList.add('receiver');
    }

    // 메시지 내용을 담을 p 요소를 생성
    const message = document.createElement('p');
    // p 요소에 메시지 텍스트를 설정
    message.textContent = content;
    // p 요소를 메시지 컨테이너에 추가
    messageContainer.appendChild(message);
    // 메시지 컨테이너를 채팅 영역에 추가
    chatArea.appendChild(messageContainer);
}




// 메시지 전송 함수
function sendMessage(event) {
    const messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        const chatMessage = {
            senderId: nickname,
            recipientId: selectedUserId,
            content: messageInput.value.trim(),
            timestamp: new Date()
        };
        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));  // 메시지 전송
        displayMessage(nickname, messageInput.value.trim());  // 화면에 메시지 표시
        messageInput.value = '';  // 입력값 초기화
    }
    chatArea.scrollTop = chatArea.scrollHeight;  // 스크롤 하단으로 이동
    event.preventDefault();
}



// 로그아웃 함수
function onLogout() {
    stompClient.send("/app/user.disconnectUser",
        {},
        JSON.stringify({nickName: nickname, fullName: fullname, status: 'OFFLINE'})
    );
    window.location.reload();  // 페이지 새로고침
}

// 이벤트 리스너 등록
usernameForm.addEventListener('submit', connect, true);  // 사용자명 폼 제출 시 connect 함수 호출
messageForm.addEventListener('submit', sendMessage, true);  // 메시지 폼 제출 시 sendMessage 함수 호출
logout.addEventListener('click', onLogout, true);  // 로그아웃 버튼 클릭 시 onLogout 함수 호출
window.onbeforeunload = () => onLogout();  // 페이지 종료 시 onLogout 함수 호출



/*
1. connect 함수 호출
-   사용자가 usernameForm을 제출하면 호출됨
-   닉네임과 전체 이름 입력값을 가져옴
-   SockJS 객체를 생성하고 stompClient를 설정
-   stompClient.connect 호출, 성공 시 onConnected 함수 호출

2. onConnected 함수 호출
-   사용자의 메시지를 받을 구독을 설정
-   공용 메시지를 받을 구독을 설정
-   stompClient.send로 사용자 등록
-   findAndDisplayConnectedUsers() 호출

3. findAndDisplayConnectedUsers 함수 호출
-   /users 엔드포인트로 연결된 사용자 목록을 요청
-   응답을 JSON으로 변환
-   현재 사용자를 제외한 사용자 목록을 필터링하고 화면에 표시

4. onMessageReceived 함수 호출 (메시지 수신 시)
-   findAndDisplayConnectedUsers() 호출
-   수신한 메시지를 콘솔에 로그
-   메시지의 보낸 사람 ID가 선택된 사용자 ID와 일치하면 displayMessage 호출
-   선택된 사용자가 있으면 해당 항목을 활성화 표시, 그렇지 않으면 messageForm 숨기기
-   메시지를 보낸 사용자가 활성화되지 않은 경우 메시지 수를 표시

5. fetchAndDisplayUserChat 함수 호출 (사용자 목록 항목 클릭 시)
-   /messages/{nickname}/{selectedUserId} 엔드포인트로 사용자 채팅 내용을 요청
-   응답을 JSON으로 변환하여 화면에 표시

6. sendMessage 함수 호출
-   사용자가 messageForm을 제출하면 호출됨
-   메시지 내용을 가져와 stompClient.send로 전송
-   displayMessage 호출로 화면에 메시지 표시

<순서 요약>
1) connect -> onConnected -> findAndDisplayConnectedUsers
2) onMessageReceived -> findAndDisplayConnectedUsers
3) fetchAndDisplayUserChat
 */
