(function () {
	'use strict';

	angular.module('nf_app').factory('regimesTributariosService', regimesTributariosService);

	regimesTributariosService.$inject = ['$http'];

	function regimesTributariosService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/regtributarios');
		}
		
		var _insert = function (regimeTributario) {
			return $http.post('http://localhost:8081/contabilizei/rest/regtributarios', regimeTributario);	
		}

		
		var _update = function (regimeTributario) {
			return $http.put('http://localhost:8081/contabilizei/rest/regtributarios', regimeTributario);	
		}

		var _findById = function (codRegimeTributario) {
			return $http.get('http://localhost:8081/contabilizei/rest/regtributarios/' + codRegimeTributario);	
		}

		return {
			findAll : _findAll,
			findById : _findById,
			insert : _insert,
			update : _update
		}
	}

})();