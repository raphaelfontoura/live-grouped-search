# ![DevSuperior logo](https://raw.githubusercontent.com/devsuperior/bds-assets/main/ds/devsuperior-logo-small.png) Estudo de caso Spring React: busca agrupada
> Live especial de lançamento da Turma 6 do Bootcamp Spring React
> 
>  *Seja um desenvolvedor full stack profissional*

## Realização
[DevSuperior - Escola de programação](https://devsuperior.com.br)

[![DevSuperior no Instagram](https://raw.githubusercontent.com/devsuperior/bds-assets/main/ds/ig-icon.png)](https://instagram.com/devsuperior.ig)
[![DevSuperior no Youtube](https://raw.githubusercontent.com/devsuperior/bds-assets/main/ds/yt-icon.png)](https://youtube.com/devsuperior)

## Nesta aula
- Back end
    - Criar modelo de domínio
    - Realizar busca agrupada com JpaRepository
    - Disponibilizar uma API REST
- Front end
    - Componentes React
    - Requisições com Axios
    - Gráfico com Apex Charts

## Live no Youtube

[![Image](https://img.youtube.com/vi/eeD1g89ihf4/mqdefault.jpg "Vídeo no Youtube")](https://youtu.be/eeD1g89ihf4)

## Vídeos básicos de referência

[![Image](https://img.youtube.com/vi/nQr_X62vq-k/mqdefault.jpg "Vídeo no Youtube")](https://youtu.be/nQr_X62vq-k)

[![Image](https://img.youtube.com/vi/eD2rEVSQaU8/mqdefault.jpg "Vídeo no Youtube")](https://youtu.be/eD2rEVSQaU8)

## Checklist back end

### Passo 1: importar projeto


### Passo 2: configuração de segurança para liberar CORS

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().anyRequest().permitAll();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
```

### Passo 3: criar modelo de domínio

[![Modelo de domínio](https://raw.githubusercontent.com/devsuperior/bds-assets/main/sds/diagrama.png)]

### Passo 4: criar DTO conforme padrão camadas adotado

```java
public class SalesByStoreDTO {

	private String storeName;
	private Double sum;
}
```

[![Modelo de domínio](https://raw.githubusercontent.com/devsuperior/bds-assets/main/sds/camadas.png)]

### Passo 5: criar SaleRepository com consulta ao banco

```java
public interface SaleRepository extends JpaRepository<Sale, Long> {

	@Query("SELECT new com.devsuperior.dssales.dto.SalesByStoreDTO(obj.store, SUM(obj.total)) "
			+ "FROM Sale AS obj "
			+ "GROUP BY obj.store")
	List<SalesByStoreDTO> salesByStore();
}
```

### Passo 6: criar Service e Controller
```java
@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	@Transactional(readOnly = true)
	public List<SalesByStoreDTO> salesByStore() {
		return repository.salesByStore();
	}
}
``` 


```java
@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/by-store")
	public ResponseEntity<List<SalesByStoreDTO>> salesByStore() {
		List<SalesByStoreDTO> list = service.salesByStore();
		return ResponseEntity.ok(list);
	}
}
```




## Checklist front end

### Passo 1: adicionar bibliotecas
```
yarn add axios

yarn add apexcharts react-apexcharts
```


### Passo 2: adicionar componente de gráfico
```ts
import Chart from 'react-apexcharts';

type ChartData = {
    labels: string[];
    series: number[];
}

const DonutChart = () => {

    const chartData : ChartData = {
        series: [477138, 499928, 444867, 220426, 473088],
        labels: ['Anakin', 'Barry Allen', 'Kal-El', 'Logan', 'Padmé']
    }

    const options = {
        legend: {
            show: true
        }
    }

    return (
        <Chart
            options={{ ...options, labels: chartData.labels }}
            series={chartData.series}
            type="donut"
            height="240"
        />
    );
}

export default DonutChart;
```

### Passo 3: adicionar estado com useState e useEffect

### Passo 4: BASE_URL e tipo SaleByStore
```ts
export const BASE_URL = "http://localhost:8080"
```

```ts
export type SaleByStore = {
    sum : number;
    storeName: string;
}
```

### Passo 5: realizar requisição com Axios

- useState e useEffect
```ts
const [chartData, setChartData] = useState<ChartData>({ labels: [], series: [] });

useEffect(() => {
    axios.get(`${BASE_URL}/sales/by-store`)
        .then(response => {
            const data = response.data as SaleByStore[];
            const myLabels = data.map(x => x.storeName);
            const mySeries = data.map(x => x.sum);

            setChartData({ labels: myLabels, series: mySeries });
        });
}, []);
```

## PARABÉNS!

![Parabéns!](https://raw.githubusercontent.com/devsuperior/bds-assets/main/img/trophy.png)

- Participe
  - Deixe seu joinha e comentário
  - Divulgue seu projeto no Linkedin e marque a DevSuperior
