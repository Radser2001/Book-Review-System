import api from "./api/axiosConfig";
import { useEffect, useState } from "react";
import Layout from "./components/Layout";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/home/Home";
import Header from "./components/header/Header";

function App() {
  const [books, setBOoks] = useState();

  const getBooks = async () => {
    try {
      const response = await api.get("/api/v1/books");

      setBOoks(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getBooks();
  }, []);

  return (
    <div>
      <Header />
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="/" element={<Home books={books} />} />
        </Route>
      </Routes>
    </div>
  );
}

export default App;
