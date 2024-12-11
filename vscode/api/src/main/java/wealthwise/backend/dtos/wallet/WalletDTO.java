package wealthwise.backend.dtos.wallet;

import wealthwise.backend.domain.Wallet;

public record WalletDTO(String name, Double risk) {
    public WalletDTO(Wallet wallet) {
        this(wallet.getName(), wallet.getRisk());
    }
}
