// 삭제 버튼의 click 이벤트 핸들러
document.querySelector('.delete').onclick = function () {
    if (!confirm('정말 삭제할까요?')) return; // 사용자가 취소 클릭시 리턴
    document.getElementById('deleteForm').submit(); // 사용자가 확인 눌렀을때 제출
}
