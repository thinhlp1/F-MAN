app.service("DataService", function () {
  let data = [];

  //GET data từ server trả về
  this.getData = function () {
    return data;
  };

  //SET data cho $scope nào đó
  this.setData = function (newData) {
    data = newData;
  };
});
