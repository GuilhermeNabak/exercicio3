// Função para cadastrar um veículo
document.getElementById('form-insert').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const tipo = document.getElementById('tipo').value;
    const combustivel = document.getElementById('combustivel').value;
    const capacidade = document.getElementById('capacidade').value;
    const velocidadeMax = document.getElementById('velocidadeMax').value;
    
    const veiculo = { tipo, combustivel, capacidade, velocidadeMax };
    
    fetch('/veiculos', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(veiculo)
    })
    .then(response => response.json())
    .then(data => {
        alert(data);
        listarVeiculos();
        document.getElementById('form-insert').reset();
    })
    .catch(error => {
        console.error('Erro ao cadastrar veículo:', error);
        alert('Erro ao cadastrar veículo.');
    });
});

// Função para listar veículos
function listarVeiculos() {
    fetch('/veiculos')
        .then(response => response.json())
        .then(data => {
            const veiculosList = document.getElementById('veiculos-list').getElementsByTagName('tbody')[0];
            veiculosList.innerHTML = ''; // Limpar a lista existente

            data.forEach(veiculo => {
                const row = veiculosList.insertRow();
                row.insertCell(0).textContent = veiculo.tipo;
                row.insertCell(1).textContent = veiculo.combustivel;
                row.insertCell(2).textContent = veiculo.capacidade;
                row.insertCell(3).textContent = veiculo.velocidadeMax;
            });
        })
        .catch(error => console.error('Erro ao carregar veículos:', error));
}

// Função para excluir veículo
document.getElementById('btn-delete').addEventListener('click', function() {
    const tipoDelete = document.getElementById('tipo-delete').value;

    fetch('/veiculos', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(tipoDelete)  
    })
    .then(response => response.json())
    .then(data => {
        alert(data);  
        listarVeiculos();  
    })
    .catch(error => console.error('Erro ao excluir veículo:', error));
});

// Função para buscar e atualizar veículo
document.getElementById('form-update').addEventListener('submit', function(e) {
    e.preventDefault();
    
    const tipo = document.getElementById('tipo-update').value;
    
    fetch(`/veiculos?tipo=${tipo}`)
        .then(response => response.json())
        .then(veiculo => {
            if (veiculo) {
                document.getElementById('update-veiculo').style.display = 'block';
                document.getElementById('combustivel-update').value = veiculo.combustivel;
                document.getElementById('capacidade-update').value = veiculo.capacidade;
                document.getElementById('velocidadeMax-update').value = veiculo.velocidadeMax;
                document.getElementById('btn-update').onclick = function() {
                    atualizarVeiculo(tipo);
                };
            } else {
                alert('Veículo não encontrado.');
            }
        })
        .catch(error => {
            console.error('Erro ao buscar veículo para atualização:', error);
            alert('Erro ao buscar veículo para atualização.');
        });
});

// Função para atualizar o veículo
function atualizarVeiculo(tipo) {
    const combustivel = document.getElementById('combustivel-update').value;
    const capacidade = document.getElementById('capacidade-update').value;
    const velocidadeMax = document.getElementById('velocidadeMax-update').value;
    
    const veiculoAtualizado = { tipo, combustivel, capacidade, velocidadeMax };
    
    fetch('/veiculos', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(veiculoAtualizado)
    })
    .then(response => response.json())
    .then(data => {
        alert(data);
        listarVeiculos();
        document.getElementById('form-update').reset();
        document.getElementById('update-veiculo').style.display = 'none'; 
    })
    .catch(error => {
        console.error('Erro ao atualizar veículo:', error);
        alert('Erro ao atualizar veículo.');
    });
}

// Carregar a lista de veículos ao iniciar a página
listarVeiculos();
