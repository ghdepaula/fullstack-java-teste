(function () {
	'use strict';

	angular.module('nf_app').factory('impostosService', impostosService);

	impostosService.$inject = ['$http'];

	function impostosService($http) {
		
		var _calcular = function (dadosImpostos) {
			return $http.post('http://localhost:8081/contabilizei/rest/impostos/calcular/', dadosImpostos);	
		}
		
		var _update = function (dadosImpostos) {
			return $http.put('http://localhost:8081/contabilizei/rest/impostos', dadosImpostos);	
		}
		
		var _findByCodCliente = function (codCliente) {
			return $http.get('http://localhost:8081/contabilizei/rest/impostos/cliente/' + codCliente);	
		}
		
		var _findAll = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/impostos');
		}
		
		var _findById = function (idImposto) {
			return $http.get('http://localhost:8081/contabilizei/rest/impostos/' + idImposto);	
		}
		

		return {
			calcular : _calcular,
			update : _update,
			findAll : _findAll,
			findById: _findById,
			findByCodCliente :_findByCodCliente
		}
	}

})();