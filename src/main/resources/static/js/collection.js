$(function () {
  var swiper = new Swiper(".flexBox", {
    // 슬라이더 사이 여백
    spaceBetween: 10,

    // 한 슬라이드에 보여줄 갯수
    slidesPerView: 5,

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
});