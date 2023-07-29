/*
 *  @: message : Nội dung thông báo
 *  @: duration: Thời gian hiển thị
 *  @: x: vị trí chiều ngang (left, center, right)
 *  @: y: vị trí chiều dọc (top, center, bottom)
 *  @: type: kiểu thông báo (sucess, error,...)
 *
 * */
const notification = (message, duration, x, y, type) => {
  const notyf = new Notyf({
    message: message,
    duration: duration,
    position: {
      x: x,
      y: y,
    },
    type: type,
  });
  switch (type) {
    case "success":
      notyf.success(message); // hiển thị thông báo thành công
      break;
    case "error":
      notyf.error(message); // hiển thị thông báo lỗi
      break;
    default:
      notyf.open({
        type: type,
        message: message,
      }); // hiển thị thông báo với kiểu và nội dung được chỉ định
      break;
  }
};
