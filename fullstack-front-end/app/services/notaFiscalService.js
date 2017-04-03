(function () {
	'use strict';

	angular.module('contabilizeiApp').factory('notaFiscalService', notaFiscalService);

	notaFiscalService.$inject = ['$http'];

	function notaFiscalService($http) {
		var _findAll = function () {
			return $http.get('http://localhost:8081/contabilizei/rest/notas');
		}
		
		var _insert = function (nota) {
			return $http.post('http://localhost:8081/contabilizei/rest/notas', nota);	
		}

		var _update = function (nota) {
			return $http.put('http://localhost:8081/contabilizei/rest/notas', nota);	
		}

		var _remove = function (idNotaFiscal) {
			return $http.delete('http://localhost:8081/contabilizei/rest/notas/' + idNotaFiscal);	
		}

		var _findById = function (idNotaFiscal) {
			return $http.get('http://localhost:8081/contabilizei/rest/notas/' + idNotaFiscal);	
		}
		
		var _findByCodCliente = function (codCliente) {
			return $http.get('http://localhost:8081/contabilizei/rest/notas/cliente/' + codCliente);	
		}
		
		var _findByCodClienteMes = function (codCliente, mesAno) {
			return $http.get('http://localhost:8081/contabilizei/rest/notas/cliente/' + codCliente + '/mes/' + mesAno);	
		}
		
		return {
			insert : _insert,
			update : _update,
			remove : _remove,
			findAll : _findAll,
			findById : _findById,
			findByCodCliente :_findByCodCliente,
			findByCodClienteMes : _findByCodClienteMes
		}
	}

})();