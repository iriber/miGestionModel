package com.migestion.test.clientes;

import com.migestion.model.Cliente;
import com.migestion.model.CondicionIVA;
import com.migestion.model.TipoCliente;
import com.migestion.model.TipoDocumento;
import com.migestion.services.IGenericService;
import com.migestion.services.ServiceFactory;
import com.migestion.services.criteria.ClienteCriteria;
import com.migestion.services.exception.ServiceException;
import com.migestion.test.TestAddEntity;

/**
 * Test para agregar clientes
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 15/10/2013
 *
 */
public class TestAddCliente extends TestAddEntity<Cliente, ClienteCriteria>{


	@Override
	protected Cliente getEntity() throws ServiceException {
		Cliente c = new Cliente();
		c.setNombre("CLIENTE MOSTRADOR");
		c.setTipoDocumento(TipoDocumento.DNI);
		c.setTipoCliente(TipoCliente.FRECUENTE);
		c.setCondicionIVA(CondicionIVA.CONSUMIDOR_FINAL);
		return c;
	}

	@Override
	protected ClienteCriteria getCriteria() {
		return new ClienteCriteria();
	}

	@Override
	protected IGenericService<Cliente, ClienteCriteria> getService() {
		return ServiceFactory.getClienteService();
	}

}
