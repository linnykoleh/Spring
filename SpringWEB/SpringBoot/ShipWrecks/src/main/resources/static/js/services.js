angular.module('app.services', []).factory('Shipwreck', function($resource) {
  return $resource('/api/v2/shipwrecks/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    }
  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
});
