package dev.ademarneto.OrderHub.service;

import dev.ademarneto.OrderHub.dto.PedidoDTO;
import dev.ademarneto.OrderHub.mapper.PedidoMapper;
import dev.ademarneto.OrderHub.model.PedidoModel;
import dev.ademarneto.OrderHub.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;
    private PedidoMapper pedidoMapper;

    public PedidoService(PedidoRepository pedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    // Criar um novo Pedido
    public PedidoDTO criarPedido(PedidoDTO pedidoDTO) {
        // Validação: Verificar se numero de pedido já existe
        if (pedidoRepository.existsByNumeroPedido(pedidoDTO.getNumeroPedido())) {
            throw new IllegalArgumentException("Já existe um pedido com este número: " + pedidoDTO.getNumeroPedido());
        }

        PedidoModel pedido = pedidoMapper.map(pedidoDTO);
        pedido = pedidoRepository.save(pedido);
        return pedidoMapper.map(pedido);
    }

    // Listar pedidos
    public List<PedidoDTO> listarPedidos() {
        List<PedidoModel> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(pedidoMapper::map)
                .collect(Collectors.toList());

    }

    // Listar pedidos por id
    public PedidoDTO listarPedidosId(Long id) {
        Optional<PedidoModel> pedidoId = pedidoRepository.findById(id);
        return pedidoId.map(pedidoMapper::map).orElse(null);

    }

    // Deletar pedido
    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    // Atualizar Pedido (por ID)
    public PedidoDTO atualizarPedido(Long id, PedidoDTO pedidoDTO) {
        PedidoModel pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com ID: " + id));

        // Atualiza os dados no objeto existente
        if (pedidoDTO.getNumeroPedido() != null)
            pedidoExistente.setNumeroPedido(pedidoDTO.getNumeroPedido());
        if (pedidoDTO.getDescricao() != null)
            pedidoExistente.setDescricao(pedidoDTO.getDescricao());
        if (pedidoDTO.getValorTotal() != null)
            pedidoExistente.setValorTotal(pedidoDTO.getValorTotal());
        if (pedidoDTO.getDataPedido() != null)
            pedidoExistente.setDataPedido(pedidoDTO.getDataPedido());
        if (pedidoDTO.getCliente() != null)
            pedidoExistente.setCliente(pedidoDTO.getCliente());

        PedidoModel pedidoSalvo = pedidoRepository.save(pedidoExistente);
        return pedidoMapper.map(pedidoSalvo);
    }

    // Busca pedido pelo numero do pedido
    public PedidoDTO buscarPorNumeroPedido(String numeroPedido) {
        Optional<PedidoModel> pedido = pedidoRepository.findByNumeroPedido(numeroPedido);
        return pedido.map(pedidoMapper::map).orElse(null);
    }

    // Deletar pedido pelo numero do pedido
    public boolean deletarPorNumeroPedido(String numeroPedido) {
        // O metodo deleteByNumeroPedido precisa ser Transacional ou usamos o find +
        // delete
        Optional<PedidoModel> pedido = pedidoRepository.findByNumeroPedido(numeroPedido);
        if (pedido.isPresent()) {
            pedidoRepository.delete(pedido.get());
            return true;
        }
        return false;
    }

    // Atualizar pedido pelo numero do pedido
    public PedidoDTO atualizarPorNumeroPedido(String numeroPedido, PedidoDTO pedidoDTO) {
        PedidoModel pedidoExistente = pedidoRepository.findByNumeroPedido(numeroPedido)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado com número: " + numeroPedido));

        // Atualização PARCIAL inteligente (só mexe no que veio preenchido)
        if (pedidoDTO.getDescricao() != null) {
            pedidoExistente.setDescricao(pedidoDTO.getDescricao());
        }

        if (pedidoDTO.getValorTotal() != null) {
            pedidoExistente.setValorTotal(pedidoDTO.getValorTotal());
        }

        if (pedidoDTO.getDataPedido() != null) {
            pedidoExistente.setDataPedido(pedidoDTO.getDataPedido());
        }

        // Não atualizamos o Cliente por segurança nesta rota simples, a menos que seja
        // necessário
        // if (pedidoDTO.getCliente() != null)
        // pedidoExistente.setCliente(pedidoDTO.getCliente());

        PedidoModel pedidoSalvo = pedidoRepository.save(pedidoExistente);
        return pedidoMapper.map(pedidoSalvo);
    }
}
