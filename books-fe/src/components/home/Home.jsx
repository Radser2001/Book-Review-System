import Books from "../books/books";
import Hero from "../hero/Hero";
import PropTypes from "prop-types";

const Home = ({ books }) => {
  console.log(books);
  return (
    <>
      <Hero />
      <Books books={books} />
    </>
  );
};

Home.propTypes = {
  books: PropTypes.array,
};
export default Home;
