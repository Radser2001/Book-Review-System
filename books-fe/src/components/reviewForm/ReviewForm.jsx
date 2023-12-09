import { Form, Button } from "react-bootstrap";
import PropTypes from "prop-types";

const ReviewForm = ({ handleSubmit, revText }) => {
  return (
    <Form>
      <Form.Group controlId="exampleForm.ControlTextarea1">
        <Form.Control
          ref={revText}
          as="textarea"
          rows={3}
          placeholder="Write your review here..."
        />
      </Form.Group>
      <Button className="mt-4" variant="primary" onClick={handleSubmit}>
        Submit
      </Button>
    </Form>
  );
};

ReviewForm.propTypes = {
  handleSubmit: PropTypes.func,
  revText: PropTypes.object,
};

export default ReviewForm;
