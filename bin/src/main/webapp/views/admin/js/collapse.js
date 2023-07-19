var isShow = true;
function isShowSetting() {
  const setting = document.getElementById("collapse-setting");
  if (isShow) {
    setting.style.transform = "rotate(180deg)";
    setting.style.transition = "all .3s ease";
  } else {
    setting.style.transform = "rotate(0deg)";
  }
  isShow = !isShow;
}
