$(function () {
  autosize($("textArea"));

  // 좋아요 버튼
  $(".like").click(function () {
    $(this).toggleClass("txtRed");
  });

  // 코멘트창 여닫기

  $(".recommentBox").hide();

  $(".recomment").click(function () {
    $(this).parent().siblings(".recommentBox").slideToggle(500);
  });

  // 글자수 세기
  $(".recommentIn").keyup(function (e) {
    let content = $(this).val();
    $(this)
      .siblings(".inputBottom")
      .children(".cntBox")
      .children(".textCnt")
      .text(content.length + "자");

    if (content.length > 1000) {
      $(this).val($(this).val().substring(0, 1000));
      alert("글자수는 1000자까지 입력 가능합니다.");
    }
  });

  // 대댓글 등록
  $(".saveBtn").click(function () {
    const comment = $(".recommentIn").val();

    var brCnt = comment.split("\n").length - 1;
    var height = brCnt * 25;

    if (brCnt < 3) {
      $("#recommentOut").text(comment);

      $("#recommentIn").val("");
    } else {
      $("#recommentOut").css({ height: height + "px" });
      $("#recommentOut").text(comment);

      $("#recommentIn").val("");
      $("#recommentIn").css({ height: "50px" });
    }
  });
});