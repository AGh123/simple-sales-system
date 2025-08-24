package com.ag.simplesalessystem.service;

import com.ag.simplesalessystem.dto.request.ClientRequest;
import com.ag.simplesalessystem.dto.response.ClientResponse;
import com.ag.simplesalessystem.entity.Client;
import com.ag.simplesalessystem.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientResponse> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ClientResponse createClient(ClientRequest request) {
        if (clientRepository.existsByMobile(request.mobile())) {
            throw new RuntimeException("Client with this mobile already exists");
        }
        Client client = new Client();
        client.setFirstName(request.firstName());
        client.setLastName(request.lastName());
        client.setMobile(request.mobile());
        Client saved = clientRepository.save(client);
        return mapToResponse(saved);
    }

    @Override
    public ClientResponse updateClient(Long id, ClientRequest request) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setFirstName(request.firstName());
        client.setLastName(request.lastName());
        client.setMobile(request.mobile());

        Client saved = clientRepository.save(client);
        return mapToResponse(saved);
    }

    private ClientResponse mapToResponse(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getMobile()
        );
    }
}
