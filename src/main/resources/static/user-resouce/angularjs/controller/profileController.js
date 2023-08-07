app.controller("profileController", function ($scope, $http,DataService, FormService, ErrorService){
    const PRODUCT_URL = "http://localhost:8080/admin/products";
    const PRODUCT_LIST_URL = "http://localhost:8080/admin/products/list";

    //Khởi tạo các biến toàn cục
    $scope.id = "";
    $scope.data = DataService.getData(); //Chứa danh sách tất cả đối tượng (User)
    $scope.form = FormService.getForm(); //Chưa đối tượng được chỉ định (Update, Create)
    $scope.errors = ErrorService.getError();
    /*NOTE: Mục đích của tạo Service là truyền dữ liệu qua lại giữa các Controller*/
    $scope.getUserProfile = function(id) {
        $http.get('/user/profile/' + id)
            .then(function(response) {
                $scope.userProfile = response.data;
            })
            .catch(function(error) {
                console.error('Error getting user profile:', error);
            });
    };
})