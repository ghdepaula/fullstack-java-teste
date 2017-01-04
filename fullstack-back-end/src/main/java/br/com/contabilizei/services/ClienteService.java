package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.dao.ClienteDAO;
import br.com.contabilizei.dto.ClienteDTO;
import br.com.contabilizei.model.Cliente;

public class ClienteService {

	private ClienteDAO daoCliente;

	public ClienteService() {
		this.daoCliente = new ClienteDAO();
	}

	public void insert(ClienteDTO clienteDTO) {
		Cliente cliente = convertToModel(clienteDTO);
		this.daoCliente.save(cliente);
	}

	public void update(ClienteDTO clienteDTO) {
		Cliente cliente = convertToModel(clienteDTO);
		this.daoCliente.update(cliente);
	}

	public List<ClienteDTO> findAll() {

		List<Cliente> clientes = this.daoCliente.findAll();

		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		for (Cliente cliente : clientes) {
			ClienteDTO clienteDTO = convertToDTO(cliente);
			clientesDTO.add(clienteDTO);
		}
		return clientesDTO;
	}

	public ClienteDTO findById(Long idCliente) {

		Cliente cliente = (Cliente) this.daoCliente.find(idCliente);

		if (cliente != null) {
			ClienteDTO clienteDTO = convertToDTO(cliente);
			return clienteDTO;
		}
		return null;
	}

	public boolean remove(Long idCliente) {

		Cliente cliente = (Cliente) this.daoCliente.find(idCliente);
		if (cliente == null) {
			return false;
		}
		Long id = cliente.getIdCliente();
		this.daoCliente.delete(id, Cliente.class);

		return true;
	}

	private ClienteDTO convertToDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();

		dto.setIdCliente(cliente.getIdCliente());
		dto.setCnpjCliente(cliente.getCnpjCliente());
		dto.setNomeRazaoSocial(cliente.getNomeRazaoSocial());
		dto.setEmail(cliente.getEmail());

		return dto;
	}

	private Cliente convertToModel(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();

		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setCnpjCliente(clienteDTO.getCnpjCliente());
		cliente.setNomeRazaoSocial(clienteDTO.getNomeRazaoSocial());
		cliente.setEmail(clienteDTO.getEmail());

		return cliente;
	}

}
