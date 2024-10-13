package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final UserRepository userRepository;

    /**
     * Construtor para injeção de dependência do UserRepository.
     *
     * @param userRepository O repositório de usuários a ser injetado.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     *
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param userRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        User user = new User();
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setCpf_cnpj(userRequestDto.getCpf_cnpj());
        user.setIs_active(userRequestDto.getIs_active());

        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setCpf_cnpj(savedUser.getCpf_cnpj());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setIs_active(savedUser.getIs_active());
        userResponseDto.setEmail(savedUser.getEmail());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto findUser(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        userResponseDto.setCpf_cnpj(user.getCpf_cnpj());
        userResponseDto.setPassword(user.getPassword());
        userResponseDto.setIs_active(user.getIs_active());
        userResponseDto.setEmail(user.getEmail());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {

        User userExist = userRepository.findById(id).orElseThrow();

        userExist.setName(userRequestDto.getName());
        userExist.setEmail(userRequestDto.getEmail());
        userExist.setPassword(userRequestDto.getPassword());
        userExist.setCpf_cnpj(userRequestDto.getCpf_cnpj());
        userExist.setIs_active(userRequestDto.getIs_active());


        userRepository.save(userExist);


        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userExist.getId());
        userResponseDto.setName(userExist.getName());
        userResponseDto.setCpf_cnpj(userExist.getCpf_cnpj());
        userResponseDto.setPassword(userExist.getPassword());
        userResponseDto.setIs_active(userExist.getIs_active());
        userResponseDto.setEmail(userExist.getEmail());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }
}
