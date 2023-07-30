app.service("FormService", function () {
  var form = {};

  //Get Data từ form (thông qua ng-model)
  this.getForm = function () {
    return form;
  };

  //Set data vào một scope nào đó
  this.setForm = function (newForm) {
    form = newForm;
  };
});
