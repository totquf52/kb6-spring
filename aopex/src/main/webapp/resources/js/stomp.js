// StompJs.Client 객체 생성
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/chat-app' // WebSocket 연결 엔드포인트
});

// 웹 소켓 에러 발생 시 콜백
stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

// STOMP 에러 발생 시 콜백
stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

// 연결 성공 시 콜백
stompClient.onConnect = (frame) => {
    console.log(frame);
    setConnected(true); // 연결 상태 UI 갱신

    // 구독 토픽 등록 및 수신 처리 핸들러 등록

    // 입장 메시지 토픽 (/topic/greetings)
    stompClient.subscribe('/topic/greetings', (greeting) => {
        console.log('/topic/greetings', greeting.body);
        showMessage(JSON.parse(greeting.body).name + '님이 입장했습니다.');
    });

    // 채팅 메시지 토픽 (/topic/chat)
    stompClient.subscribe('/topic/chat', (chat) => {
        console.log('/topic/chat', chat.body);
        const message = JSON.parse(chat.body);
        showMessage(`${message.name}:${message.content}`);
    });

    // 연결 성공 시 입장 메시지 보내기
    const name = document.getElementById('name').value;
    stompClient.publish({
        destination: '/app/hello', // GreetingMessage에 대응
        body: JSON.stringify({ name })
    });
};

// 연결 상태에 따른 버튼 속성 변경
function setConnected(connected) {
    const connectBtn = document.getElementById('connect');
    const disconnectBtn = document.getElementById('disconnect');
    const messages = document.getElementById('chat-messages');

    connectBtn.disabled = connected;
    disconnectBtn.disabled = !connected;
    messages.innerHTML = ''; // 이전 메시지 초기화
}

// 웹소켓 연결 요청
function connect() {
    stompClient.activate(); // 연결 시작
}

// 연결 끊기
function disconnect() {
    stompClient.deactivate(); // 연결 종료
    setConnected(false);
    console.log('Disconnected');
}

// 메시지 전송 처리
function sendMessage() {
    const name = document.getElementById('name').value;
    const content = document.getElementById('content').value;
    console.log({ name, content });

    stompClient.publish({
        destination: '/app/chat', // ChatMessage에 대응
        body: JSON.stringify({ name, content })
    });
}

// 수신 메시지를 채팅창에 출력
function showMessage(message) {
    const messages = document.getElementById('chat-messages');
    messages.innerHTML += '<tr><td>' + message + '</td></tr>';
}

// 이벤트 핸들러 설정
window.addEventListener('DOMContentLoaded', (event) => {
    const forms = document.querySelectorAll('.form-inline');
    const connectBtn = document.getElementById('connect');
    const disconnectBtn = document.getElementById('disconnect');
    const sendBtn = document.getElementById('send');

    connectBtn.addEventListener('click', () => connect());
    disconnectBtn.addEventListener('click', () => disconnect());
    sendBtn.addEventListener('click', () => sendMessage());

    for (const form of forms) {
        console.log(form);
        form.addEventListener('submit', (e) => e.preventDefault()); // form submit 기본 동작 방지
    }
});