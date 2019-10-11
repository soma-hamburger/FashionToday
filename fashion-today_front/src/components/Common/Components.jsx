import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';

export const ClickDiv = ({ children, onClick, className, onKeyPress }) => (
  <div
    aria-hidden
    role="button"
    onClick={onClick}
    onKeyPress={onKeyPress}
    className={className}
  >
    {children}
  </div>
);

ClickDiv.defaultProps = {
  onKeyPress: () => {},
  className: '',
};

ClickDiv.propTypes = {
  children: PropTypes.element.isRequired,
  onClick: PropTypes.func.isRequired,
  className: PropTypes.string,
  onKeyPress: PropTypes.func,
};

export const ClickImg = ({ src, alt, onClick, className }) => (
  <ClickDiv onClick={onClick} className={className}>
    <img src={src} alt={alt} />
  </ClickDiv>
);

ClickImg.defaultProps = {
  className: '',
  alt: 'defalt',
};

ClickImg.propTypes = {
  src: PropTypes.string.isRequired,
  alt: PropTypes.string,
  onClick: PropTypes.func.isRequired,
  className: PropTypes.string,
};

export const LinkImg = ({ src, alt, to, className, href }) => {
  if (href)
    return (
      <a href={href} className={className}>
        <img src={src} alt={alt} />
      </a>
    );
  return (
    <Link to={to} className={className}>
      <img src={src} alt={alt} />
    </Link>
  );
};

LinkImg.defaultProps = {
  className: '',
  alt: 'defalt',
  to: null,
  href: null,
};

LinkImg.propTypes = {
  src: PropTypes.string.isRequired,
  alt: PropTypes.string,
  to: PropTypes.string,
  href: PropTypes.string,
  className: PropTypes.string,
};

export const Test = () => <div className="default">default</div>;
