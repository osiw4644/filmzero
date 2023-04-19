$(function () {
  // 슬라이드
  var shopSwiper = new Swiper(".giftShopContainer", {
    // 슬라이더 사이 여백
    spaceBetween: 0,

    // 한 슬라이드에 보여줄 갯수
    slidesPerView: 4,

    centeredSlides: true, //센터모드

    allowTouchMove: false,

    // 자동 슬라이드
    autoplay: {
      delay: 2500,
      disableOnInteraction: false, // false-스와이프 후 자동 재생
    },

    loop: true, // 슬라이드 반복 여부

    // 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
    loopAdditionalSlides: 1,

    navigation: {
      nextEl: ".nextBtn",
      prevEl: ".preBtn",
    },
  });

  // 기프트샵 메뉴 효과

  $(".ottBox").css({ width: "38%" });
  $(".moviePro").hide();
  $(".vodPro").hide();

  $(".ottBox").click(function () {
    $(this).css({ width: "38%" });
    $(this).siblings().css({ width: "28%" });
    $(".ottPro").show(500);
    $(".moviePro").hide(500);
    $(".vodPro").hide(500);
  });

  $(".movieBox").click(function () {
    $(this).css({ width: "38%" });
    $(this).siblings().css({ width: "28%" });
    $(".moviePro").show(500);
    $(".ottPro").hide(500);
    $(".vodPro").hide(500);
  });

  $(".vodBox").click(function () {
    $(this).css({ width: "38%" });
    $(this).siblings().css({ width: "28%" });
    $(".vodPro").show(500);
    $(".moviePro").hide(500);
    $(".ottPro").hide(500);
  });
});