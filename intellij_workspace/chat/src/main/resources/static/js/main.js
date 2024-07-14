'use strict';

// DOM 요소를 선택하여 변수에 저장
var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null; // STOMP 클라이언트를 저장할 변수
var username = null; // 사용자의 이름을 저장할 변수

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

// WebSocket 연결을 설정하는 함수
function connect(event) {
    username = document.querySelector('#name').value.trim(); // 입력된 사용자 이름을 가져옴

    if(username) {
        usernamePage.classList.add('hidden'); // 사용자 이름 입력 페이지 숨기기
        chatPage.classList.remove('hidden'); // 채팅 페이지 보이기

        var socket = new SockJS('/ws'); // 새로운 SockJS 객체 생성
        stompClient = Stomp.over(socket); // STOMP 클라이언트 생성

        stompClient.connect({}, onConnected, onError); // WebSocket 연결 시도
    }
    event.preventDefault(); // 폼 제출 기본 동작 방지
}

// WebSocket 연결 성공 시 호출되는 함수
function onConnected() {
    // 공용 토픽에 구독
    stompClient.subscribe('/topic/public', onMessageReceived);

    // 서버에 사용자 이름을 알림
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify({sender: username, type: 'JOIN'})
    )

    connectingElement.classList.add('hidden'); // 연결 중 메시지 숨기기
}

// WebSocket 연결 실패 시 호출되는 함수
function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red'; // 에러 메시지 표시
}

// 메시지를 보내는 함수
function sendMessage(event) {
    var messageContent = messageInput.value.trim(); // 입력된 메시지 내용 가져오기
    if(messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value,
            type: 'CHAT'
        };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage)); // 메시지 전송
        messageInput.value = ''; // 입력 필드 비우기
    }
    event.preventDefault(); // 폼 제출 기본 동작 방지
}

// 메시지를 수신했을 때 호출되는 함수
function onMessageReceived(payload) {
    var message = JSON.parse(payload.body); // 메시지 내용을 파싱

    var messageElement = document.createElement('li');

    if(message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!'; // 사용자 참여 메시지
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!'; // 사용자 퇴장 메시지
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender); // 아바타 색상 설정

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement); // 메시지 영역에 추가
    messageArea.scrollTop = messageArea.scrollHeight; // 스크롤을 맨 아래로 이동
}

// 사용자 이름에 따른 아바타 색상을 계산하는 함수
function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i); // 해시 값 계산
    }
    var index = Math.abs(hash % colors.length); // 색상 인덱스 계산
    return colors[index]; // 색상 반환
}

// 폼 제출 이벤트에 대한 이벤트 리스너 추가
usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)
