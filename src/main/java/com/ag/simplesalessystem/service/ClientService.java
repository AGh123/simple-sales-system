package com.ag.simplesalessystem.service;

import com.ag.simplesalessystem.dto.request.ClientRequest;
import com.ag.simplesalessystem.dto.response.ClientResponse;

import java.util.List;

public interface ClientService {
    List<ClientResponse> getAllClients();

    ClientResponse createClient(ClientRequest request);

    ClientResponse updateClient(Long id, ClientRequest request);
}
