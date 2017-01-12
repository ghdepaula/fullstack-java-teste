(function () {
	'use strict';

	angular.module('nf_app').controller('clientesController', clientesController);

	clientesController.$inject = ['clienteService','anexoService', '$scope', '$filter'];

	function clientesController(clienteService, anexoService, $scope, $filter) {
		
		$scope.clientes = [];
		$scope.cliente;
		$scope.anexos = [];
		$scope.anexosChecked = [];
		$scope.hideAdd = false;

		$scope.findAll = function () {
			findAll();
		}
		
		$scope.findAnexosActives = function(){
			findAnexosActives();
		}

		$scope.loadForm = function (idCliente) {
			clienteService.findById(idCliente).success(function (data) {
				$scope.cliente = data;
				$scope.hideAdd = true;

				$('#cnpjCliente').attr('readonly', 'cnpjCliente');
			});
		}

		$scope.insert = function (cliente) {
			
			cliente.anexos = $scope.anexos;
			
			clienteService.insert(cliente).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		$scope.update = function (cliente) {
			clienteService.update(cliente).success(function (data) {
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}

		$scope.remove = function (idCliente) {
			clienteService.remove(idCliente).success(function (data) {				
				clearData();
			})
			.error(function (data) {
				showError(data);
			});
		}
		
		$scope.toggleChecked = function(anexo) {
			console.log(anexo.checked);
		    console.log($scope.anexosChecked);
		}

		function findAll() {
			clienteService.findAll().success(function(result){
				$scope.clientes = result;
			});
		}
		
		function findAnexosActives(){
			anexoService.findByStatus(true).success(function(result){
				$scope.anexos = result;
			});
		}
		
		function clearData() {
			$scope.cliente = null;
			$scope.hideAdd = false;

			resetCliente();
			findAll();
			findAnexosActives();
		}
		
		$scope.cancel = function () {
			clearData();
		}

		function resetCliente() {
			$('#nomeRazaoSocial').val('');
			$('#codAnexo').val('')
			$('#cnpjCliente').val('');
			$('#telefone').val('');
			$('#email').val('');
			$('#cnpjCliente').removeAttr('readonly', 'cnpjCliente');
		}

		function showError(data) {
			alert("Infelizmente ocorreu um erro. Verifique seus dados e tente novamente mais tarde");
		}
	}

})();