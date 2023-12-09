import PropTypes from "prop-types";
import api from "../../api/axiosConfig";
import { useEffect, useRef } from "react";
import { useParams } from "react-router-dom";
import ReviewForm from "../reviewForm/ReviewForm";

const Reviews = ({ getBookData, book, reviews, setReviews }) => {
  console.log(book);
  console.log(reviews);
  const formatDate = (dateString) => {
    const options = { year: "numeric", month: "long", day: "numeric" };
    const date = new Date(dateString);
    return date.toLocaleDateString("en-US", options);
  };

  const addReview = async () => {
    try {
      const response = await api.post(`/api/v1/reviews`, {
        reviewBody: revText.current.value,
        isbn: book?.isbn,
      });

      console.log(response.data);
      setReviews([...reviews, response.data]);
      revText.current.value = "";
    } catch (error) {
      console.log(error);
    }
  };

  const revText = useRef();
  let params = useParams();
  const isbn = params.isbn;

  useEffect(() => {
    getBookData(isbn);
  }, [isbn]);

  return (
    <div>
      <div className="card m-5" style={{ maxWidth: "100%" }}>
        <div className="row no-gutters">
          <div className="col-md-4">
            <img
              src={book?.thumbnailUrl}
              height="600"
              width="100%"
              className="card-img"
              alt={book?.title}
            />
          </div>
          <div className="col-md-8">
            <div className="card-body">
              <h2
                className="card-title "
                style={{ fontSize: "36px", fontWeight: "500" }}
              >
                {book?.title}
              </h2>
              <p className="card-text mb-3">{book?.longDescription}</p>

              <p
                className="card-text mb-3"
                style={{
                  marginBottom: "10",
                }}
              >
                <strong style={{ fontWeight: "500", marginRight: "4px" }}>
                  Authors:
                </strong>
                {book?.authors.map((author, index) => (
                  <span key={author}>
                    {index > 0 && ", "} {author}
                  </span>
                ))}
              </p>

              <p
                className="card-text mb-3"
                style={{
                  marginBottom: "10",
                }}
              >
                <strong style={{ fontWeight: "500" }}>Published Date:</strong>{" "}
                {formatDate(book?.publishedDate)}
              </p>
              <p
                className="card-text mb-3"
                style={{
                  marginBottom: "10",
                }}
              >
                <strong style={{ fontWeight: "500" }}> ISBN:</strong>{" "}
                {book?.isbn}
              </p>
              <p
                className="card-text mb-3"
                style={{
                  marginBottom: "10",
                }}
              >
                <strong style={{ fontWeight: "500" }}>Page Count:</strong>{" "}
                {book?.pageCount}
              </p>

              <p
                className="card-text mb-3"
                style={{
                  marginBottom: "10",
                }}
              >
                <strong style={{ fontWeight: "500", marginRight: "4px" }}>
                  Categories:
                </strong>
                {book?.categories.map((category, index) => (
                  <span key={category}>
                    {index > 0 && ", "} {category}
                  </span>
                ))}
              </p>
            </div>
          </div>
        </div>
      </div>
      <div style={{ margin: "5rem" }}>
        <h2>Reviews</h2>
        {reviews?.length > 0 ? (
          reviews?.map((review) => (
            <p className="lead" key={review?._id}>
              {review?.body}
            </p>
          ))
        ) : (
          <h5>No reviews yet</h5>
        )}
      </div>
      <div style={{ margin: "5rem" }}>
        <h2>Add your review</h2>
        <ReviewForm handleSubmit={addReview} revText={revText} />
      </div>
    </div>
  );
};

Reviews.propTypes = {
  getBookData: PropTypes.func,
  book: PropTypes.object,
  reviews: PropTypes.array,
  setReviews: PropTypes.func,
};
export default Reviews;
