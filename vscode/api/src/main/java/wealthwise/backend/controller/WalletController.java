package wealthwise.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import wealthwise.backend.domain.Wallet;
import wealthwise.backend.dtos.wallet.WalletRequestDTO;
import wealthwise.backend.dtos.wallet.WalletResponseDTO;
import wealthwise.backend.repositories.WalletRepository;
import wealthwise.backend.services.WalletService;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/wallets")
public class WalletController {
    
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletService walletService;

    @GetMapping
    public ResponseEntity<List<WalletResponseDTO>> getAll() {
        
        List<WalletResponseDTO> wallets = this.walletRepository.findAll().stream().map(WalletResponseDTO::new).toList();
        return ResponseEntity.ok(wallets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDTO> getId(@PathVariable String id) {
        
        Wallet wallet = walletService.getWalletById(id);
        WalletResponseDTO walletDTO = new WalletResponseDTO(wallet);
        return ResponseEntity.ok(walletDTO);
    }

    @PostMapping
    public ResponseEntity<WalletResponseDTO> postWallet(@RequestBody @Valid WalletRequestDTO request) {

        WalletResponseDTO walletDTO = walletService.createWallet(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(walletDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<WalletResponseDTO> patchWallet(@RequestBody Map<String, Object> updates, @PathVariable String id) {
        
        WalletResponseDTO walletDTO = walletService.updateWallet(updates, id);
        return ResponseEntity.status(HttpStatus.OK).body(walletDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWallet(@PathVariable String id) {
        
        walletService.deleteWallet(id);
        return ResponseEntity.ok("Wallet deleted with id '" + id + "'");
    }
}
