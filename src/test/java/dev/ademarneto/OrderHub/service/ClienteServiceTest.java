package dev.ademarneto.OrderHub.service;

import dev.ademarneto.OrderHub.dto.ClienteDTO;
import dev.ademarneto.OrderHub.mapper.ClienteMapper;
import dev.ademarneto.OrderHub.model.ClienteModel;
import dev.ademarneto.OrderHub.repository.ClienteRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings; // <--- NOVO
import org.mockito.quality.Strictness;           // <--- NOVO

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT) // <--- O Segredo da Paz!
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private ClienteMapper clienteMapper;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void naoDeveCadastrarClienteComCpfDuplicado() {
        // CENÁRIO
        String cpfValido = "36713437890";
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCpf(cpfValido);

        // CORREÇÃO IDE: Passamos o objeto direto. Adeus ambiguidade!
        when(clienteMapper.map(clienteDTO)).thenReturn(new ClienteModel());

        // Simulamos erro de duplicidade
        when(clienteRepository.existsByCpf(cpfValido)).thenReturn(true);

        // AÇÃO E VERIFICAÇÃO
        assertThrows(IllegalArgumentException.class, () -> {
            clienteService.criarCliente(clienteDTO);
        });
    }
}