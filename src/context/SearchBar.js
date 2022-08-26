import React, {useState} from 'react'


const SearchBar = () => { const [searchInput, setSearchInput] = useState(""); 

const categories = [  
    { name: "Clothing" },
  { name: "Electronics" },
  { name: "Outdoors" },
  { name: "Accessories" },
  { name: "Games" },
  { name: "School" },
  { name: "Automotive" },
  { name: "Handmade" },
  { name: "Pets" },
  { name: "Kids" },];
  
  const handleChange = (e) => {
  e.preventDefault();
  setSearchInput(e.target.value);
};

if (searchInput.length > 0) {
    categories.filter((category) => {
    return category.name.match(searchInput);
});
}

return (
<div>
    <input
   type="search"
   placeholder="Search here"
   onChange={handleChange}
   value={searchInput} />
   <table>
  <tr>
  </tr>{categories.map((category, index) => {
  <div>
  <tr>
    <td>{category.name}</td>
  </tr>
</div>})}

</table>
</div>
)

};

export default SearchBar;