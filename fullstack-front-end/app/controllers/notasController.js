(function () {
	'use strict';

	angular.module('nf_app').controller('notasController', notasController);

	notasController.$inject = ['clienteService','anexoService', 'notaFiscalService', '$scope', '$filter'];

	function notasController(clienteService, anexoService, notaFiscalService, $scope, $filter) {
		
		$scope.notas = [];
		$scope.nota;
		$scope.codCliente;
		$scope.clientes = [];
		$scope.anexos = [];
		$scope.hideAdd = false;

		$scope.findAll = function () {
			findAll();
		}
		
		$scope.findClientes = function(){
			findClientes();
		}
		
		$scope.loadForm = function (numNotaFiscal) {
			notaFiscalService.findById(numNotaFiscal).success(function (data) {
				$scope.nota = data;
				$scope.hideAdd = true;
				
			});
		}

		$scope.insert = function (nota) {
			
			notaFiscalService.insert(nota).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		$scope.update = function (nota) {
			
			notaFiscalService.update(nota).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		$scope.remove = function (numNotaFiscal) {
			notaFiscalService.remove(numNotaFiscal).success(function (data) {				
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		function findAll() {
			notaFiscalService.findAll().success(function(result){
				$scope.notas = result;
			});
		}
		
		function findClientes() {
			clienteService.findAll().success(function(result){
				$scope.clientes = result;
			});
		}
		
		function clearData() {
			$scope.nota = null;
			$scope.clientes = [];
			$scope.anexos = [];
			$scope.hideAdd = false;

			resetNotaFiscal();
			findAll();
		}
		
		$scope.cancel = function () {
			clearData();
		}

		function resetNotaFiscal() {
			$('#numNotaFiscal').val('');
			$('#dtEmissaoNota').val('')
			$('#descrNotaFiscal').val('');
			$('#vlrNotaFiscal').val('');
		}

		function showError(data) {
			alert("Infelizmente ocorreu um erro. Verifique seus dados e tente novamente mais tarde");
		}
	}

})();