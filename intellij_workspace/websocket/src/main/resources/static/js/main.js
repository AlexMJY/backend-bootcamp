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

        const socket = new SockJS('/ws');  // SockJS 객체 생성
        stompClient = Stomp.over(socket);  // STOMP 클라이언트 생성

        stompClient.connect({}, onConnected, onError);  // 서버에 연결
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

// 연결된 사용자 목록을 가져와 표시하는 함수
async function findAndDisplayConnectedUsers() {
    const connectedUsersResponse = await fetch('/users');  // 사용자 목록 요청
    let connectedUsers = await connectedUsersResponse.json();  // 응답 JSON으로 변환
    connectedUsers = connectedUsers.filter(user => user.nickName !== nickname);  // 현재 사용자는 제외
    const connectedUsersList = document.getElementById('connectedUsers');
    connectedUsersList.innerHTML = '';  // 목록 초기화

    connectedUsers.forEach(user => {
        appendUserElement(user, connectedUsersList);  // 사용자 요소 추가
        if (connectedUsers.indexOf(user) < connectedUsers.length - 1) {
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
    document.querySelectorAll('.user-item').forEach(item => {
        item.classList.remove('active');
    });
    messageForm.classList.remove('hidden');  // 메시지 폼 표시

    const clickedUser = event.currentTarget;
    clickedUser.classList.add('active');  // 활성화된 사용자 항목 표시

    selectedUserId = clickedUser.getAttribute('id');  // 선택된 사용자 ID 저장
    fetchAndDisplayUserChat().then();  // 사용자 채팅 내용 가져오기

    const nbrMsg = clickedUser.querySelector('.nbr-msg');
    nbrMsg.classList.add('hidden');  // 메시지 수 숨기기
    nbrMsg.textContent = '0';  // 메시지 수 초기화
}

// 메시지를 화면에 표시하는 함수
function displayMessage(senderId, content) {
    const messageContainer = document.createElement('div');
    messageContainer.classList.add('message');
    if (senderId === nickname) {
        messageContainer.classList.add('sender');  // 발신자 메시지 스타일
    } else {
        messageContainer.classList.add('receiver');  // 수신자 메시지 스타일
    }
    const message = document.createElement('p');
    message.textContent = content;
    messageContainer.appendChild(message);
    chatArea.appendChild(messageContainer);
}

// 사용자 채팅 내용을 가져와 표시하는 함수
async function fetchAndDisplayUserChat() {
    const userChatResponse = await fetch(`/messages/${nickname}/${selectedUserId}`);
    const userChat = await userChatResponse.json();
    chatArea.innerHTML = '';  // 채팅 영역 초기화
    userChat.forEach(chat => {
        displayMessage(chat.senderId, chat.content);  // 각 메시지 표시
    });
    chatArea.scrollTop = chatArea.scrollHeight;  // 스크롤 하단으로 이동
}

// 연결 실패 시 호출되는 함수
function onError() {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';  // 오류 메시지 표시
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

// 메시지 수신 시 호출되는 함수
async function onMessageReceived(payload) {
    await findAndDisplayConnectedUsers();  // 연결된 사용자 목록 갱신
    console.log('Message received', payload);
    const message = JSON.parse(payload.body);
    if (selectedUserId && selectedUserId === message.senderId) {
        displayMessage(message.senderId, message.content);  // 메시지 표시
        chatArea.scrollTop = chatArea.scrollHeight;  // 스크롤 하단으로 이동
    }

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
