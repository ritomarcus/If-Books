const API_BASE_URL = 'http://localhost:8080';

const apiAutores = {
  listar: () => $.ajax({ url: `${API_BASE_URL}/autores`, method: 'GET' }),
  buscarPorId: (id) => $.ajax({ url: `${API_BASE_URL}/autores/${id}`, method: 'GET' }),
  criar: (autor) => $.ajax({ url: `${API_BASE_URL}/autores`, method: 'POST', contentType: 'application/json', data: JSON.stringify(autor) }),
  atualizar: (id, autor) => $.ajax({ url: `${API_BASE_URL}/autores/${id}`, method: 'PUT', contentType: 'application/json', data: JSON.stringify(autor) }),
  deletar: (id) => $.ajax({ url: `${API_BASE_URL}/autores/${id}`, method: 'DELETE' }),
};

const apiLivros = {
  listar: () => $.ajax({ url: `${API_BASE_URL}/livros`, method: 'GET' }),
  buscarPorId: (id) => $.ajax({ url: `${API_BASE_URL}/livros/${id}`, method: 'GET' }),
  criar: (livro) => $.ajax({ url: `${API_BASE_URL}/livros`, method: 'POST', contentType: 'application/json', data: JSON.stringify(livro) }),
  atualizar: (id, livro) => $.ajax({ url: `${API_BASE_URL}/livros/${id}`, method: 'PUT', contentType: 'application/json', data: JSON.stringify(livro) }),
  deletar: (id) => $.ajax({ url: `${API_BASE_URL}/livros/${id}`, method: 'DELETE' }),
};
