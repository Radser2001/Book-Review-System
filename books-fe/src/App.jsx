import api from "./api/axiosConfig";
import { useEffect, useState } from "react";
import Layout from "./components/Layout";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Home from "./components/home/Home";
import Header from "./components/header/Header";
import Reviews from "./components/reviews/Reviews";

function App() {
  const [books, setBooks] = useState();
  const [book, setBook] = useState();
  const [reviews, setReviews] = useState();

  const getBooks = async () => {
    try {
      const response = await api.get("/api/v1/books");
      console.log(response.data);
      setBooks(response.data);
    } catch (error) {
      console.log(error);
    }
  };

  const getBookData = async (isbn) => {
    try {
      const response = await api.get(`/api/v1/books/${isbn}`);
      console.log(response.data);
      setBook(response.data);
      setReviews(response.data.reviewIds);
    } catch (error) {
      console.log(error);
    }
  };

  useEffect(() => {
    getBooks();
  }, []);

  return (
    <>
      <Header />
      <Routes>
        <Route path="/*" element={<Layout />}>
          <Route index element={<Home books={books} />} />
          <Route
            path="Reviews/:isbn"
            element={
              <Reviews
                book={book}
                getBookData={getBookData}
                reviews={reviews}
                setReviews={setReviews}
              />
            }
          />
        </Route>
      </Routes>
    </>
  );
}

export default App;
