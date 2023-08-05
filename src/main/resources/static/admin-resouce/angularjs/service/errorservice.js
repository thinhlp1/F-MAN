app.service("ErrorService", function () {
  let errs = {};

  //GET err từ server trả về
  this.getError = function () {
    return errs;
  };

  //SET err cho $scope nào đó
  this.setError = function (newErr) {
    errs = newErr;
  };
});
