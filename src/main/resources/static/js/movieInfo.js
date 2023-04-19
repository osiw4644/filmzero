$(function () {
  // 코멘트 슬라이드
  var swiper = new Swiper(".commentContainer", {
    // 슬라이더 사이 여백
    spaceBetween: 10,

    // 한 슬라이드에 보여줄 갯수
    slidesPerView: 4,

    loop: false, // 슬라이드 반복 여부

    // 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
    loopAdditionalSlides: 1,

    // 버튼
    navigation: {
      nextEl: ".nextBtn1",
      prevEl: ".preBtn1",
    },

    watchOverflow: true,

    allowTouchMove: false,
  });

  // 코멘트 하트
  $(".heartBtn i").click(function () {
    $(this).toggleClass("colorRed");
  });

  // 트레일러 슬라이드
  var swiper2 = new Swiper(".trailerContainer", {
    // 슬라이더 사이 여백
    spaceBetween: 20,

    // 한 슬라이드에 보여줄 갯수
    slidesPerView: 3,

    loop: true, // 슬라이드 반복 여부

    // 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
    loopAdditionalSlides: 1,

    allowTouchMove: false,

    autoplay: {
      delay: 2500, // 시간 설정
      disableOnInteraction: false, // false-스와이프 후 자동 재생
    },

    // 버튼
    navigation: {
      nextEl: ".nextBtn2",
      prevEl: ".preBtn2",
    },
  });

  // 갤러리 슬라이드
  var swiper3 = new Swiper(".galleryContainer", {
    // 슬라이더 사이 여백
    spaceBetween: 10,

    // 한 슬라이드에 보여줄 갯수
    slidesPerView: 4,

    loop: true, // 슬라이드 반복 여부

    // 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
    loopAdditionalSlides: 1,

    allowTouchMove: false,

    autoplay: {
      delay: 2500, // 시간 설정
      disableOnInteraction: false, // false-스와이프 후 자동 재생
    },

    // 버튼
    navigation: {
      nextEl: ".nextBtn3",
      prevEl: ".preBtn3",
    },
  });

  // 모달 슬라이드
  var swiper4 = new Swiper(".modalContainer", {
    // 슬라이더 사이 여백
    spaceBetween: 0,

    // 한 슬라이드에 보여줄 갯수
    slidesPerView: 1,

    loop: true, // 슬라이드 반복 여부

    // 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
    loopAdditionalSlides: 1,

    allowTouchMove: false,

    autoplay: {
      delay: 2500, // 시간 설정
      disableOnInteraction: false, // false-스와이프 후 자동 재생
    },

    // 버튼
    navigation: {
      nextEl: ".nextBtn4",
      prevEl: ".preBtn4",
    },
  });

  // 모달창 여닫기
  $(".GallerySeiton .imgBox").click(function () {
    $(".modal").css({ display: "block" });
  });

  $(".modalClose").click(function () {
    $(".modal").css({ display: "none" });
  });

  // 별점 클릭으로 코멘트 입력창 출력
  $("input[name=rating]").click(function () {
    var checked = $("input[name=rating]");
    var rate = $("input[name=rating]:checked").val();
    $(".comement").css({ display: "block" });

    if (rate == 0.5) {
      $(".scoreBox").text("🤬");
      $(".comementBox p").text(
        "작품이 별로였군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 1) {
      $(".scoreBox").text("😡");
      $(".comementBox p").text(
        "작품이 별로였군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 1.5) {
      $(".scoreBox").text("😠");
      $(".comementBox p").text(
        "작품이 별로였군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 2) {
      $(".scoreBox").text("😟");
      $(".comementBox p").text(
        "작품이 별로였군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 2.5) {
      $(".scoreBox").text("😞");
      $(".comementBox p").text(
        "그냥 그런 작품이었군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 3) {
      $(".scoreBox").text("🙂");
      $(".comementBox p").text(
        "괜찮은 작품이었군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 3.5) {
      $(".scoreBox").text("😊");
      $(".comementBox p").text(
        "재미있는 작품이었군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 4) {
      $(".scoreBox").text("🤗");
      $(".comementBox p").text(
        "흥미진진한 작품이었군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 4.5) {
      $(".scoreBox").text("🥰");
      $(".comementBox p").text(
        "좋은 작품이었군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    } else if (rate == 5) {
      $(".scoreBox").text("😍");
      $(".comementBox p").text(
        "최고의 작품이었군요! 이 작품에 대한 평가를 글로 남겨보세요"
      );
    }
  });

  // 코멘트 모달 여닫기
  $(".commentBtn").click(function () {
    $(".commentInput").css({ display: "block" });
  });

  $(".closeBtn").click(function () {
    $(".commentInput").css({ display: "none" });
  });

  // 글자수 세기
  $("#comment").keyup(function (e) {
    let content = $(this).val();

    if (content.length == 0 || content == "") {
      $(".textCnt").text("0자");
    } else {
      $(".textCnt").text(content.length + "자");
    }

    if (content.length > 1000) {
      $(this).val($(this).val().substring(0, 1000));
      alert("글자수는 100000자까지 입력 가능합니다.");
    }
  });

  // 코멘트 등록

  $(".saveBtn").click(function () {
    var comment = $("#comment").val();

    $(".commentInput").css({ display: "none" });
    $(".comement").css({ display: "none" });
    $(".myComment").css({ display: "block" });

    $(".myComment p").text(comment);
  });

  // 코멘트 수정
  $(".modiBtn").click(function () {
    $(".commentInput").css({ display: "block" });
  });

  // 코멘트 삭제
  $(".delBtn").click(function () {
    $(".comement").css({ display: "none" });
    $(".myComment").css({ display: "none" });

    $("input[name=rating]:checked").prop("checked", false);
    $(".scoreBox").text("");
  });
});