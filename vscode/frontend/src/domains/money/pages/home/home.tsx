import { Conteiner, Header, WalletCard, SearchBar } from "./styled";
import img from "../../../../assets/avatar01.jpg";

import { useState, useEffect } from "react";
import { Outlet } from "react-router-dom";
import BarLat from "../../components/bar_lat/bar_lat";
import { FiMoreVertical } from "react-icons/fi";

interface Wallet {
  id: number;
  name: string;
  risk: number;
}

export default function Home() {
  const [wallets, setWallets] = useState<Wallet[]>([]);
  const [filteredWallets, setFilteredWallets] = useState<Wallet[]>([]);
  const [searchQuery, setSearchQuery] = useState("");
  const [token] = useState("");
  const [userId] = useState("");
  const [username] = useState("");
  const [email] = useState("");

  useEffect(() => {
    // Fetch wallets from the API on component mount
    async function fetchWallets() {
      const url = import.meta.env.VITE_API_LIST_MONEYS;
      const response = await fetch(url, {
        method: "GET",
        credentials: "include",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        const data = await response.json();
        setWallets(data);
        setFilteredWallets(data); // Set initial filtered list
      } else {
        alert("Failed to fetch wallets.");
      }
    }

    fetchWallets();
  }, []);

  // Update filtered wallets based on search query
  useEffect(() => {
    setFilteredWallets(
      wallets.filter((wallet) =>
        wallet.name.toLowerCase().includes(searchQuery.toLowerCase())
      )
    );
  }, [searchQuery, wallets]);

  // Handle wallet deletion
  async function handleDeleteWallet(walletId: number) {
    const confirmDelete = window.confirm("Are you sure you want to delete this wallet?");
    if (!confirmDelete) return;

    const url = import.meta.env.VITE_API_DELETE_MONEYS;
    const response = await fetch(`${url}/${walletId}`, {
      method: "DELETE",
      credentials: "include",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (response.ok) {
      alert("Wallet deleted successfully!");
      // Remove wallet from the state
      setWallets((prevWallets) => prevWallets.filter((wallet) => wallet.id !== walletId));
    } else {
      alert("Failed to delete wallet.");
    }
  }

  return (
    <div style={{ display: "flex", flexDirection: "row", backgroundColor: "#1F1F1F" }}>
      <BarLat />
      <Conteiner style={{ marginTop: "100px", marginLeft: "100px", flex: 1 }}>
        <Header>
          <div>
            <img src={img} alt="User Profile" />
            <h1>Welcome Back!</h1>
          </div>
        </Header>
      
        {/* Search Bar */}
        <div style={{ marginTop: "20px", marginBottom: "20px" }}>
          <SearchBar
            type="text"
            placeholder="Search wallets..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
          />
        </div>

        {/* Wallet Section */}
        <div className="wallets">
          {filteredWallets.map((wallet) => (
            <WalletCard key={wallet.id}>
              <div className="wallet-info">
                <h3>{wallet.name}</h3>
                <p>Risk: {wallet.risk}</p>
              </div>
              <div className="menu">
                <FiMoreVertical
                  size={24}
                  onClick={() => handleDeleteWallet(wallet.id)}
                  style={{ cursor: "pointer" }}
                  title="Delete Wallet"
                />
              </div>
            </WalletCard>
          ))}
        </div>
      </Conteiner>
    </div>
  );
}
