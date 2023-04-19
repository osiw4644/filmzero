$(function () {
  // AOS 실행
  AOS.init();

  // 메인 사이즈 작으면 푸터 고정하기
  var mainHei = $(".main").height();

  if (mainHei < 730) {
    $(".footer").addClass("footerFix");
  }

  // 탑버튼 분기
  $(".bottomUtill").hide();

  $(".bottomUtill").click(function () {
    $(".utillBtn").toggleClass("active");
  });

  // FnQ 내용 확인

  $(".answer").hide();

  $(".FnQsetion .contentBox .flexBox").click(function () {
    var display = $(this).next(".answer").css("display");

    if (display === "none") {
      $(this).next(".answer").show();
    } else {
      $(this).next(".answer").hide();
    }
  });
});

// 탑버튼 표시
$(window).scroll(function () {
  var height = $(document).scrollTop();

  if (height >= 50) {
    $(".bottomUtill").fadeIn(500);
  } else {
    $(".bottomUtill").fadeOut(500);
  }
});

// 로그인
function login() {
  var id = $("#id").val();
  var pw = $("#pw").val();

  if (id == "") {
    alert("아이디를 입력해주세요.");
  } else if (pw == "") {
    alert("비밀번호를 입력해주세요.");
  } else {
    alert("로그인 완료");
  }
}

// 회원가입
$(function () {
  // 전체 동의 체크시 필수, 선택항목 모두 동의
  $("input[name=chk]").prop("checked", true);
  $("#checkAll").prop("checked", true);

  $("#checkAll").click(function () {
    if ($("#checkAll").is(":checked"))
      $("input[name=chk]").prop("checked", true);
    else $("input[name=chk]").prop("checked", false);
  });

  $("input[name=chk]").click(function () {
    var total = $("input[name=chk]").length;
    var checked = $("input[name=chk]:checked").length;

    if (total != checked) $("#checkAll").prop("checked", false);
    else $("#checkAll").prop("checked", true);
  });

  // 동의서 내용 확인
  $(".joinSection .termsArea").hide();

  $(".joinSection .moreView").click(function () {
    $(this).parent().next().slideToggle();
  });
});

function join() {
  const id = $("#email").val();
  const pw = $("#pw").val();
  const pwChk = $("#pwChk").val();
  const name = $("#name").val();
  const birth = $("#birth").val();
  const nickname = $("#nickname").val();
  const tel = $("#tel").val();

  const idReg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  const pwReg = /^[a-zA-Z0-9]{6,12}$/;
  const nameReg = /^[가-힣]{2,6}$/;
  const birthReg = /^[0-9]{8}$/;
  const nicknameReg = /^[a-zA-Z0-9가-힣]{2,8}$/;
  const telReg = /^[0-9]{10,11}$/;

  if (!idReg.test(id)) {
    alert("아이디 형식을 다시 확인해주세요.");
    $("#id").focus();
  } else if (!pwReg.test(pw)) {
    alert("비밀번호 형식을 다시 확인해주세요.");
    $("#pw").focus();
  } else if (pw != pwChk) {
    alert("비밀번호를 다시 확인해주세요");
    $("#passchk").focus();
  } else if (!nameReg.test(name)) {
    alert("이름에는 한글만 입력해주세요. (공백 입력 불가)");
    $("#name").focus();
  } else if (!birthReg.test(birth)) {
    alert("생년월일을 다시 확인해주세요.");
    $("#birth").focus();
  } else if (!nicknameReg.test(nickname)) {
    alert("닉네임 형식을 다시 확인해주세요.");
    $("#nickname").focus();
  } else if (!telReg.test(tel)) {
    alert("전화번호를 다시 확인해주세요. 휴대폰 번호만 입력이 가능합니다.");
    $("#tel").focus();
  } else if (!$(".required").is(":checked")) {
    alert("필수 동의 항목을 확인해주세요.");
  } else {
    alert("회원가입이 완료되었습니다.");
  }
}