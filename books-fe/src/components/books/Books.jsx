import PropTypes from "prop-types";
import { useNavigate } from "react-router-dom";

const Books = ({ books }) => {
  const navigate = useNavigate();
  const handleAddReview = (isbn) => {
    navigate(`/Reviews/${isbn}`);
  };
  const formatDate = (date) => {
    const newDate = new Date(date);
    return newDate.toLocaleDateString("en-US", {
      year: "numeric",
      month: "long",
      day: "numeric",
    });
  };

  return (
    <div className="album py-5 bg-body-tertiary">
      <div className="container">
        {books ? (
          <div className="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-5">
            {books.map((book) => (
              <div className="col" key={book.isbn}>
                <div className="card rounded shadow-sm">
                  <img
                    width="200"
                    height="400"
                    className="img-thumbnail rounded card-img-top"
                    src={book.thumbnailUrl}
                    alt={book.title}
                  />

                  <div className="card-body">
                    <p
                      className="card-text lead"
                      style={{
                        fontSize: "22px",
                        fontWeight: "500",
                        marginBottom: "10px",
                      }}
                    >
                      {book.title}
                    </p>
                    <p
                      className="card-text"
                      style={{
                        marginBottom: "0",
                      }}
                    >
                      <strong style={{ fontWeight: "500" }}>
                        Published Date:
                      </strong>{" "}
                      {formatDate(book.publishedDate)}
                    </p>
                    <p
                      className="card-text"
                      style={{
                        marginBottom: "0",
                      }}
                    >
                      <strong style={{ fontWeight: "500" }}> ISBN:</strong>{" "}
                      {book.isbn}
                    </p>
                    <p
                      className="card-text"
                      style={{
                        marginBottom: "0",
                      }}
                    >
                      <strong style={{ fontWeight: "500" }}>Page Count:</strong>{" "}
                      {book.pageCount}
                    </p>
                    <p
                      className="card-text"
                      style={{
                        marginBottom: "0",
                      }}
                    >
                      <strong style={{ fontWeight: "500" }}> Status:</strong>{" "}
                      {book.status}
                    </p>
                    <button
                      onClick={() => handleAddReview(book.isbn)}
                      type="button"
                      className="btn btn-primary float-end"
                    >
                      Add a Review
                    </button>
                  </div>
                </div>
              </div>
            ))}
          </div>
        ) : (
          <h1 className="text-center">Loading</h1>
        )}
      </div>
    </div>
  );
};

Books.propTypes = {
  books: PropTypes.array,
};
export default Books;
