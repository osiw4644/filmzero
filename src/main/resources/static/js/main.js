$(function () {
  // 메인 무비차트 슬라이드

  var swiper = new Swiper(".chartContainer", {
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

    // 버튼
    navigation: {
      nextEl: ".nextBtn",
      prevEl: ".preBtn",
    },
  });

  var swiper2 = new Swiper(".miniContainer", {
    // 슬라이더 사이 여백
    spaceBetween: 0,

    // 한 슬라이드에 보여줄 갯수
    slidesPerView: 1,

    // 자동 슬라이드
    autoplay: {
      delay: 2500,
      disableOnInteraction: false, // false-스와이프 후 자동 재생
    },

    loop: true, // 슬라이드 반복 여부

    // 슬라이드 반복 시 마지막 슬라이드에서 다음 슬라이드가 보여지지 않는 현상 수정
    loopAdditionalSlides: 1,

    // 스태퍼
    pagination: {
      el: ".pagination", //버튼을 담을 태그 설정
      clickable: true, // 버튼 클릭 여부
    },

    // 버튼
    navigation: {
      nextEl: ".nextBtnMini",
      prevEl: ".preBtnMini",
    },
  });

  var swiper3 = new Swiper(".colContainer", {
    // 슬라이더 사이 여백
    spaceBetween: 5,

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

    // 버튼
    navigation: {
      nextEl: ".button_next",
      prevEl: ".button_pre",
    },
  });
});