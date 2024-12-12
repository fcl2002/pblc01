package wealthwise.backend.dtos.wallet;

import java.util.List;

import wealthwise.backend.domain.Asset;
import wealthwise.backend.domain.Wallet;

public record WalletResponseDTO(String name, Double risk , List<Asset> assets) {
    public WalletResponseDTO(Wallet wallet) {
        this(wallet.getName(), wallet.getRisk(), wallet.getAssets());
    }
}
