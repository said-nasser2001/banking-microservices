package com.bankSystem.accounts.service.impel;


import com.bankSystem.accounts.dto.AccountsDto;
import com.bankSystem.accounts.dto.CardsDto;
import com.bankSystem.accounts.dto.CustomerDetailsDto;
import com.bankSystem.accounts.dto.LoansDto;
import com.bankSystem.accounts.entity.Accounts;
import com.bankSystem.accounts.entity.Customer;
import com.bankSystem.accounts.exception.ResourceNotFoundException;
import com.bankSystem.accounts.mapper.AccountsMapper;
import com.bankSystem.accounts.mapper.CustomerMapper;
import com.bankSystem.accounts.repository.AccountsRepository;
import com.bankSystem.accounts.repository.CustomerRepository;
import com.bankSystem.accounts.service.ICustomersService;
import com.bankSystem.accounts.service.client.CardsFeignClient;
import com.bankSystem.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto( customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;

    }
}
