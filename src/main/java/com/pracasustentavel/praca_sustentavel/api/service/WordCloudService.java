package com.pracasustentavel.praca_sustentavel.api.service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.pracasustentavel.praca_sustentavel.api.entity.WordCount;
import com.pracasustentavel.praca_sustentavel.api.repository.SurveyResponseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WordCloudService {

    private final SurveyResponseRepository repository;
    private final List<String> stopWords = List.of(
            "a", "à", "ao", "aos", "as", "até", "com", "como", "da", "das", "de", "dela", "dele", "deles", "demais",
            "depois", "do", "dos", "e", "é", "ela", "ele", "eles", "em", "entre", "era", "essa", "esse", "esta",
            "está", "estas", "estava", "este", "estes", "eu", "foi", "foram", "há", "isso", "isto", "já",
            "lhe", "lhes", "mais", "mas", "meu", "minha", "muito", "na", "nas", "nem", "no", "nos", "nós", "nossa",
            "o", "os", "ou", "para", "pela", "pelas", "pelo", "pelos", "por", "qual", "quando", "que", "quem", "se",
            "seja", "sem", "será", "seu", "sua", "são", "só", "também", "teu", "tu", "um", "uma", "umas",
            // Adicione mais stop words conforme necessário
            // Palavras comuns em português que não agregam valor semântico
            "praça", "praca", "sustentável", "sustentavel",
            "a", "o", "as", "os", "um", "uma", "uns", "umas", "ao", "à", "aos", "às",
            "do", "da", "dos", "das", "no", "na", "nos", "nas", "pelo", "pela", "pelos", "pelas",

            // Pronomes
            "eu", "tu", "ele", "ela", "nós", "vós", "eles", "elas", "me", "mim", "comigo",
            "te", "ti", "contigo", "se", "si", "consigo", "lhe", "nos", "vos", "lhes",
            "meu", "minha", "meus", "minhas", "teu", "tua", "teus", "tuas", "seu", "sua",
            "seus", "suas", "nosso", "nossa", "nossos", "nossas", "vosso", "vossa", "vossos", "vossas",

            // Preposições
            "de", "em", "por", "para", "com", "sem", "sob", "sobre", "entre", "até",
            "desde", "contra", "perante", "através", "além", "dentro", "fora", "perto",
            "longe", "durante",

            // Conjunções
            "e", "mas", "ou", "porque", "pois", "que", "se", "como", "quando", "embora",
            "porém", "todavia", "contudo", "então", "também", "apesar", "caso", "além disso",
            "portanto", "logo", "ainda que", "a fim de", "com o objetivo de",

            // Verbos auxiliares e comuns
            "ser", "estar", "ter", "haver", "ir", "vir", "fazer", "poder", "dever",
            "querer", "saber", "está", "estão", "tem", "tenho", "é",

            // Demonstrativos
            "esse", "essa", "isso", "este", "esta", "isto", "aquele", "aquela", "aquilo",
            "aqueles", "aquelas", "deste", "desta", "destes", "destas", "disso", "daquilo",
            "nisto", "naquilo",

            // Localizadores
            "lá", "aqui", "ali", "onde", "aonde",

            // Interjeições e expressões comuns
            "ah", "oh", "ei", "oi", "olá", "opa", "eita", "nossa", "caramba", "poxa",
            "uau", "xi", "ih", "ué", "hein", "que que é isso", "quê",

            // Gírias e expressões coloquiais
            "cara", "mano", "mina", "véi", "velho", "brother", "meu", "meu chapa",
            "mano do céu", "parça", "parceiro", "camarada", "meu rei", "minha rainha",
            "bro", "meu anjo", "fera", "chefe", "paizão", "mainha", "veinho", "velhinho",
            "moleque", "garoto", "menino", "menina", "guri", "guria", "piá", "piázinho",
            "gajo", "gaja", "bacana", "maneiro", "massa", "show", "top", "legal", "beleza",
            "joia", "firmeza", "daora", "dahora", "da hora", "responsa", "sinistro", "brabo",
            "brabíssimo", "irado", "supimpa", "bala", "zica", "animal", "monstro", "mito", "lenda",

            // Abreviações e siglas comuns na internet
            "pq", "tb", "tbm", "vc", "cê", "ce", "c", "mt", "mto", "mta", "td", "tdo",
            "tda", "hj", "amg", "amigo", "amiga", "bjs", "bjss", "bjo", "bjao", "vlw",
            "flw", "fvr", "por favor", "abs", "vdd", "aff", "pfv", "pfvr", "sla", "slc",
            "sdds", "tmj", "tamo junto", "fds", "findi", "blz", "bele", "falou", "falows",
            "fmz", "fmza", "mds", "oxe", "oxi", "vey", "véi", "vix", "vish", "vcs", "tá",
            "tô", "tamos", "tamo", "cadê", "d", "pro", "pra", "q", "qualé", "tipo", "né",
            "qnd", "aki", "vamo", "vambora", "partiu", "bora", "falô", "blza", "sup", "obg",
            "pls", "ñ", "num", "msm", "sdd", "pqn", "pqns", "agr", "poxa", "po", "uai",
            "eita", "trem", "bixim", "vish", "causo", "sô", "ôxe",

            // Regionalismos
            "bão", "ocê", "oxente", "painho", "égua", "bah", "tchê", "aham", "ahã",
            "orra meu", "home", "homi", "muié", "muler", "visse", "tu", "num é", "nera",

            // Marcadores de discurso
            "tipo assim", "aí", "pois é", "quer dizer", "sabe", "entende", "sacou",
            "tá ligado", "ok", "okay", "tranquilo", "suave", "de boa", "fechou",
            "combinado", "entendido",

            // Expressões de afirmação e negação
            "s", "n", "yep", "nop", "ahan", "nops", "de jeito nenhum", "com certeza",
            "claro", "óbvio", "lógico", "evidente", "sem dúvida", "quem sabe", "vai ver",
            "pode ser",

            // Expressões de quantidade e intensidade
            "bastante", "pra caramba", "pra cacete", "pra dedéu", "pácas", "biga",
            "uma porrada", "um monte", "um tantão", "um bocado", "um tiquinho",
            "um cadinho", "um tico", "uma belezura",

            // Expressões de concordância
            "tá bom", "tá bem", "tá certo", "pode crer", "tô dentro", "topo", "vamos",
            "bora lá", "tá de boa",

            // Expressões de discordância
            "tá nada", "que nada", "nem a pau", "nem ferrando", "nem morto",
            "nem que a vaca tussa", "tá louco", "tá doido", "nem pensar",

            // Expressões de despedida
            "tchau", "até logo", "até mais", "fui", "to indo", "té mais", "inté",
            "bye", "xau", "beijo", "abraço", "abç", "fique com Deus", "vai com Deus",
            "se cuida", "se cuide", "fica bem", "fique bem",

            // Expressões de saudação
            "e aí", "fala", "fala aí", "fala tu", "salve", "quali", "como vai",
            "como tá", "tudo bem", "tudo bom", "eae", "coé", "alô",

            // Expressões de agradecimento
            "valeu", "obrigado", "obrigada", "brigado", "brigadão", "muito obrigado",
            "grato", "gratidão", "thanks", "thx", "tanks", "tenks",

            // Termos de internet e redes sociais
            "rs", "kkkk", "haha", "hehe", "lol", "risos", "kkk", "hahaha", "postar",
            "post", "stories", "story", "feed", "timeline", "curtir", "like", "reagir",
            "compartilhar", "share", "seguir", "follow", "unfollow", "bio", "trending",
            "viral", "viralizar",

            // Expressões de dúvida
            "será", "será mesmo", "não sei não", "tô na dúvida", "to em dúvida",
            "vai saber", "sei lá", "não faço ideia", "nem imagino",

            // Conectores expandidos
            "tanto quanto", "bem como", "a menos que", "a não ser que", "conforme",
            "à medida que", "sempre que", "logo que", "assim que", "uma vez que",
            "na medida em que", "de modo que", "de forma que", "caso contrário",
            "fora isso", "além do mais", "por outro lado", "por um lado",
            "de uma forma ou de outra", "apesar de tudo", "ao contrário",
            "em contrapartida", "em compensação", "por isso mesmo", "desse modo",
            "desta forma", "assim sendo", "por consequência", "consequentemente",
            "para tanto", "aliás", "inclusive", "ademais", "com isso", "por assim dizer",
            "em suma", "afinal", "a fim de que", "de modo a",

            // Expressões expandidas
            "você", "vocês", "porquê", "entaum", "bls", "dexa", "tá ligado", "tá sabendo",
            "tá com", "tá ok", "cê tá", "ocê", "ocês", "nois", "nois é", "é nois",
            "na moral", "escuta só", "né não", "nu", "rlx", "susto", "tru", "brodi",
            "eu to", "eu tô", "nois tá", "nóis tá", "tu tá", "tu vai", "c vai", "tu vamo",
            "tá suave", "tá de boaça"
    );

    public List<WordCount> generateWordCloudForImprovement() {
        return generateWordCloud("improvement");
    }

    public List<WordCount> generateWordCloudForActivities() {
        return generateWordCloud("activities");
    }

    public List<WordCount> generateWordCloudForSustainability() {
        return generateWordCloud("sustainability");
    }

    private List<WordCount> generateWordCloud(String field) {
        return repository.findAll()
                .stream()
                .map(response -> switch (field) {
                    case "improvement" -> response.getImprovement();
                    case "activities" -> response.getActivities();
                    case "sustainability" -> response.getSustainability();
                    default -> null;
                })
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .collect(Collectors.collectingAndThen(Collectors.toList(), this::processTexts));

    }

    private List<WordCount> processTexts(List<String> texts) {
        return texts.stream()
                .map(text -> text.replaceAll("[^a-záàâãéèêíïóôõöúçñ\\s]", "").toLowerCase().split("\\s+"))
                .flatMap(Stream::of)
                .filter(Predicate.not(stopWords::contains))
                .collect(Collectors.toMap(Function.identity(), str -> 1, Integer::sum))
                .entrySet()
                .stream()
                .map(WordCount::of)
                .sorted(Comparator.comparingInt(WordCount::getCount))
                .limit(30)
                .collect(Collectors.toList());
    }
}