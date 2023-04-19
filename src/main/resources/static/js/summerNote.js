$(function () {
  $("#summerNote").summernote({
    height: 300, // 에디터 높이
    minHeight: 400, // 최소 높이
    maxHeight: 400, // 최대 높이
    focus: true, // 에디터 로딩후 포커스를 맞출지 여부
    lang: "ko-KR", // 한글 설정
    toolbar: [
      // [groupName, [list of button]]
      ["fontsize", ["fontsize"]],
      ["style", ["bold", "italic", "underline", "strikethrough", "clear"]],
      ["color", ["forecolor", "color"]],
    ],
    fontSizes: [
      "8",
      "9",
      "10",
      "11",
      "12",
      "14",
      "16",
      "18",
      "20",
      "22",
      "24",
      "28",
      "30",
      "36",
      "50",
      "72",
    ],

    placeholder: "내용을 입력하세요.",
  });
});