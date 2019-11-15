import React from 'react';
import PropTypes from 'prop-types';
import { Link } from 'react-router-dom';
import bagIcon from '../../img/category/bag_icon.png';
import jeanIcon from '../../img/category/jean_icon.png';
import hatIcon from '../../img/category/hat_icon.png';
import dressIcon from '../../img/category/dress_icon.png';
import shoesIcon from '../../img/category/shoes_icon.png';
import shirtsIcon from '../../img/category/shirts_icon.png';
import teeIcon from '../../img/category/tee_icon.png';
import accesoryIcon from '../../img/category/accesory_icon.png';

export const getCategoryIcon = category => {
  const categories = new Map([
    { jean: jeanIcon },
    { bag: bagIcon },
    { hat: hatIcon },
    { dress: dressIcon },
    { shirts: shirtsIcon },
    { tee: teeIcon },
    { accesory: accesoryIcon },
    { shoes: shoesIcon },
  ]);

  if (!categories.has(category)) {
    return null;
  }

  const src = categories.get(category);

  return (
    <div className="categoryIcon">
      <img src={src} alt="categoryIcon" />
    </div>
  );
};

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

export const LinkDiv = ({ children, to, className, href }) => {
  if (to)
    return (
      <Link to={to}>
        <div className={className}>{children}</div>
      </Link>
    );
  return (
    <a href={href}>
      <div className={className}>{children}</div>
    </a>
  );
};

LinkDiv.defaultProps = {
  className: '',
  to: null,
  href: null,
};

LinkDiv.propTypes = {
  children: PropTypes.element.isRequired,
  to: PropTypes.string,
  className: PropTypes.string,
  href: PropTypes.string,
};

export const ClickImg = ({ src, alt, onClick, className }) => (
  <ClickDiv onClick={onClick} className={className}>
    <img src={src} alt={alt} className="inheritImage" />
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
        <img src={src} alt={alt} className="inheritImage" />
      </a>
    );
  return (
    <Link to={to} className={className}>
      <img src={src} alt={alt} className="inheritImage" />
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
