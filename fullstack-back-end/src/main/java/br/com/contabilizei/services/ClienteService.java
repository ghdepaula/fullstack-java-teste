package br.com.contabilizei.services;

import java.util.ArrayList;
import java.util.List;

import br.com.contabilizei.dao.ClienteDAO;
import br.com.contabilizei.dto.AnexoDTO;
import br.com.contabilizei.dto.ClienteDTO;
import br.com.contabilizei.dto.RegimeTributarioDTO;
import br.com.contabilizei.model.Anexo;
import br.com.contabilizei.model.Cliente;

/**
 * Classe responsável por fornecer métodos para aplicação de regras de negócio, processamento e conversão de dados envolvendo operações com clientes. 
 * 
 * @author Guilherme Henrique de Paula 
 * 
 */
public class ClienteService {

	private ClienteDAO daoCliente;
	
	private AnexoService anexoService;
	
	private RegimesTributariosService regimesTributariosService;

	public ClienteService() {
		this.daoCliente = new ClienteDAO();
		this.anexoService = new AnexoService();
		this.regimesTributariosService = new RegimesTributariosService();
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link ClienteDTO} para inserção de uma instância da entidade {@link Cliente}.
	 * 
	 * @param clienteDTO instância de {@link ClienteDTO} contendo os dados da entidade {@link Cliente} que será inserida.
	 * 
	 */
	public void insert(ClienteDTO clienteDTO) {
		Cliente cliente = convertToModel(clienteDTO);
		this.daoCliente.save(cliente);
	}

	/**
	 * Método Java que processa os dados de uma instância de {@link ClienteDTO} para atualização de uma instância da entidade {@link Cliente}.
	 * 
	 * @param clienteDTO instância de {@link ClienteDTO} contendo os dados da entidade {@link Cliente} que será atualizada.
	 */
	public void update(ClienteDTO clienteDTO) {
		Cliente cliente = convertToModel(clienteDTO);
		this.daoCliente.update(cliente);
	}

	/**
	 * Método que lista todas as entidades de {@link Cliente} e realiza a conversão de dados para uma {@link List<ClienteDTO>}.
	 * 
	 * @return clientesDTO lista de {@link ClienteDTO}
	 */
	public List<ClienteDTO> findAll() {

		List<Cliente> clientes = this.daoCliente.findAll();

		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		for (Cliente cliente : clientes) {
			ClienteDTO clienteDTO = convertToDTO(cliente);
			clientesDTO.add(clienteDTO);
		}
		return clientesDTO;
	}

	/**
	 * Método que busca uma instância da entidade {@link Cliente} com base no seu código identificador e realiza a conversão de dados para uma instância de {@link ClienteDTO}.
	 * 
	 * @param idCliente código identificado da entidade {@link Cliente}
	 * @return clienteDTO instância de {@link ClienteDTO} ou {@link <code>null</code>} caso nenhum registro seja encontrado.
	 */
	public ClienteDTO findById(Long idCliente) {

		Cliente cliente = this.daoCliente.find(idCliente);

		if (cliente != null) {
			ClienteDTO clienteDTO = convertToDTO(cliente);
			return clienteDTO;
		}
		return null;
	}

	/**
	 * Método que executa a conversão de uma instância da entidade {@link Cliente} para uma instância de {@link ClienteDTO}
	 * 
	 * @param cliente instância da entidade {@link Cliente}
	 * @return dto instância de {@link ClienteDTO}
	 * 
	 */
	public ClienteDTO convertToDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		List<AnexoDTO> anexos = anexoService.convertoToDTO(cliente.getAnexos());
		RegimeTributarioDTO regimeTributario = this.regimesTributariosService.convertToDTO(cliente.getRegimeTributario());

		dto.setIdCliente(cliente.getIdCliente());
		dto.setCnpjCliente(cliente.getCnpjCliente());
		dto.setNomeRazaoSocial(cliente.getNomeRazaoSocial());
		dto.setEmail(cliente.getEmail());
		dto.setCodRegimeTributario(cliente.getCodRegimeTributario());
		dto.setRegimeTributario(regimeTributario);
		dto.setAnexos(anexos);

		return dto;
	}

	/**
	 * Método que executa a conversão de uma instância de {@link ClienteDTO} para uma instância da entidade {@link Cliente} 
	 * 
	 * @param clienteDTO instância de {@link ClienteDTO}
	 * @return cliente instância da entidade {@link Cliente}
	 * 
	 */
	public Cliente convertToModel(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		List<Anexo> anexos = anexoService.convertToModel(clienteDTO.getAnexos());

		cliente.setIdCliente(clienteDTO.getIdCliente());
		cliente.setCnpjCliente(clienteDTO.getCnpjCliente());
		cliente.setNomeRazaoSocial(clienteDTO.getNomeRazaoSocial());
		cliente.setCodRegimeTributario(clienteDTO.getCodRegimeTributario());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setAnexos(anexos);

		return cliente;
	}

}
