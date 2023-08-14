app.controller('SidebarController', function($scope) {
    $scope.activeTab = ''; // Biến lưu trạng thái tab đang được chọn

    $scope.setActiveTab = function(tab) {
        $scope.activeTab = tab;
    };

    $scope.isTabActive = function(tab) {
        return $scope.activeTab === tab;
    };
});