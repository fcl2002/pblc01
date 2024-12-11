package wealthwise.backend.dtos.wallet;

import wealthwise.backend.domain.Wallet;

public record WalletResponseDTO(String name, Double risk /*,  /*List<Assets> assets*/) {
    public WalletResponseDTO(Wallet wallet) {
        this(wallet.getName(), wallet.getRisk()/*, wallet.getAssets() */);
    }
}
