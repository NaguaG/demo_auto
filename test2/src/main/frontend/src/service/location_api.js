const BASE_URL = 'http://localhost:8080/api/v1/location';

const LocationAPI = function () { };

LocationAPI.parseResponse = async function (response) {
  const body = await response.text();
  const json = JSON.parse(body);

  return json;
};

LocationAPI.getSuggestions = async function (query) {
  const requestOptions = {
    method: 'GET',
    redirect: 'follow'
  };

  let actionUrl = `${BASE_URL}/search/${encodeURIComponent(query)}`;

  const response = await fetch(actionUrl, requestOptions);

  return LocationAPI.parseResponse(response);
}

export default LocationAPI;