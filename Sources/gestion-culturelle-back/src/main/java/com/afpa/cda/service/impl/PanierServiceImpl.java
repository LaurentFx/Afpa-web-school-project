//package com.afpa.cda.service.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.afpa.cda.dao.ArticleRepository;
//import com.afpa.cda.dao.ManifestationRepository;
//import com.afpa.cda.dao.PanierRepository;
//import com.afpa.cda.dao.UserRepository;
//import com.afpa.cda.dto.ArticleDto;
//import com.afpa.cda.dto.ManifestationDto;
//import com.afpa.cda.dto.PanierDto;
//import com.afpa.cda.entity.Article;
//import com.afpa.cda.entity.Manifestation;
//import com.afpa.cda.entity.Panier;
//import com.afpa.cda.entity.User;
//import com.afpa.cda.service.IPanierService;
//
//@Service
//public class PanierServiceImpl implements IPanierService {
//	@Autowired
//	private PanierRepository panierRepository;
//	@Autowired
//	private ArticleRepository articleRepository;
//	@Autowired
//	private ManifestationRepository manifestationRepository;
//	@Autowired
//	private ModelMapper modelMapper;
//	@Autowired
//	private UserRepository userRepository;
//
//
//	@Override
//	public List<PanierDto> findAll() {
//
//		return this.panierRepository.findAll()
//				.stream()
//				.map(p -> {
//					PanierDto panierDto = new PanierDto();
//					panierDto.setId(p.getId());
//					panierDto.setDateValidation(p.getDateValidation());
//					panierDto.setTotal(p.getTotal());
//
//					panierDto.setListArticles(new ArrayList<ArticleDto>());
//
//					return panierDto;
//				})
//				.collect(Collectors.toList());
//	}
//
//	@Override
//	public boolean addArticlePanier (ArticleDto articleDto) {
//
//		Optional <Panier> panierOp=panierRepository.findById(articleDto.getPanier().getId());
//		Optional <Manifestation> manifestationOp=manifestationRepository.findById(articleDto.getManifestation().getId());
//		PanierDto panierDto = new PanierDto();
//		ManifestationDto manifestationDto = new ManifestationDto();
//
//		if (panierOp.isPresent() && manifestationOp.isPresent()) {
//			panierDto = modelMapper.map(panierOp.get(), PanierDto.class);
//			manifestationDto = modelMapper.map(manifestationOp.get(),ManifestationDto.class);
//
//			if (manifestationDto.getReservations() >= articleDto.getQuantite()) {
//				this.articleRepository.save(this.modelMapper.map(articleDto, Article.class));
//				panierDto.setTotal(panierDto.getTotal()+articleDto.getQuantite()*manifestationDto.getPrixBillet());
//				manifestationDto.setReservations(manifestationDto.getReservations()-articleDto.getQuantite());
//				//				DateFormat df = new SimpleDateFormat("dd/MM/yy");
//				Date dateobj = new Date();
//				panierDto.setDateValidation(dateobj);
//				this.panierRepository.save(this.modelMapper.map(panierDto, Panier.class));
//				this.manifestationRepository.save(this.modelMapper.map(manifestationDto, Manifestation.class));
//				return false;
//			} else {
//				System.err.println("Pas assez de billets dispo"); }
//				return true;
//		}
//		return true;
//
//	}
//
//	// inutile
////	@Override
////	public PanierDto add(PanierDto panier) {
////		Panier panE = this.panierRepository.save(this.modelMapper.map(panier, Panier.class));
////		panier.setId(panE.getId());
////		return panier;
////	}
//
//	@Override
//	public boolean updatePanier(PanierDto panier, int id) {
//		Optional<Panier> panUp = this.panierRepository.findById(id);
//		if (panUp.isPresent()) {
//			this.panierRepository.save(this.modelMapper.map(panier, Panier.class));
//			return true;
//		}
//		return false;
//	}
//
//
//
//	@Override
//	public PanierDto findById(int id) {
//		Optional<Panier> panE = this.panierRepository.findById(id);
//		PanierDto panDto = new PanierDto();
//
//		if (panE.isPresent()) {
//
//			Panier pan= panE.get();
//			panDto.setId(pan.getId());
//			panDto.setDateValidation(pan.getDateValidation());
//			panDto.setTotal(pan.getTotal());
//
//		}
//		return panDto;
//	}
//
//	@Override
//	public PanierDto findByUser(int id) {
//		Optional <User> userOp=this.userRepository.findById(id);
//		PanierDto panierDto = new PanierDto ();
//		if (userOp.isPresent()) {
//			Panier panier = userOp.get().getPanier();
//			panierDto = modelMapper.map(panier,PanierDto.class);
//		}
//
//		return panierDto;
//	}
//
//	@Override
//	public boolean deletePanier(int id) {
//		Optional<Panier> panierOp = this.panierRepository.findById(id);
//
//		if (panierOp.isPresent()) {
//			List<Article> listArticles = this.articleRepository.findAll();
//			List<Article> listArticlesByPanier = new ArrayList<Article>();
//			for (Article article : listArticles) {
//				if (article.getPanier().getId()==id) {
//					listArticlesByPanier.add(article);
//				}
//			}
//
//			for (Article article : listArticlesByPanier) {
//				this.articleRepository.deleteById(article.getId());
//			}
//
//			PanierDto panierDto = modelMapper.map(panierOp.get(),PanierDto.class);
//			panierDto.setTotal(0);
//			panierRepository.save(this.modelMapper.map(panierDto,Panier.class));	
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public boolean deleteArticles(int id) {
//		Optional<Panier> panierOp =	this.panierRepository.findById(id);
//		if (panierOp.isPresent()) {
//			List<Article> listArticles = this.articleRepository.findAll();
//			List<Article> listArticlesByPanier = new ArrayList<Article>();
//			for (Article article : listArticles) {
//				if (article.getPanier().getId()==id) {
//					listArticlesByPanier.add(article);
//				}
//			}
//
//			PanierDto panierDto = modelMapper.map(panierOp.get(),PanierDto.class);
//			ArticleDto articleDto = new ArticleDto();
//			ManifestationDto manifestationDto = new ManifestationDto ();
//
//			for (Article article : listArticlesByPanier) {
//				articleDto= modelMapper.map(article,ArticleDto.class);
//				manifestationDto = articleDto.getManifestation();
//				manifestationDto.setReservations(manifestationDto.getReservations()+articleDto.getQuantite());
//				panierDto.setTotal(panierDto.getTotal()-(manifestationDto.getPrixBillet()*articleDto.getQuantite()));
//				this.manifestationRepository.save(this.modelMapper.map(manifestationDto,Manifestation.class));
//				this.panierRepository.save(this.modelMapper.map(panierDto,Panier.class));
//			}
//			this.articleRepository.deleteAll();
//			return true;
//		}
//return false;
//	}
//	
//}