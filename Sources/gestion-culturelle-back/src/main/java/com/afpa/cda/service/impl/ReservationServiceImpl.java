package com.afpa.cda.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afpa.cda.dao.ManifestationRepository;
import com.afpa.cda.dao.ReservationRepository;
import com.afpa.cda.dao.UserRepository;
import com.afpa.cda.dto.AnimationDto;
import com.afpa.cda.dto.ManifestationDto;
import com.afpa.cda.dto.ReservationDto;
import com.afpa.cda.dto.SalleDto;
import com.afpa.cda.dto.UserDto;
import com.afpa.cda.entity.Manifestation;
import com.afpa.cda.entity.Reservation;
import com.afpa.cda.entity.User;
import com.afpa.cda.service.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ManifestationRepository manifestationRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;

	
	@Override
	public List<ReservationDto> findAll() {
		return this.reservationRepository.findAll().stream().map(r -> {
			ReservationDto reservationDto = new ReservationDto();

			reservationDto.setId(r.getId());

			ManifestationDto manifestationDto = new ManifestationDto();
			manifestationDto.setId(r.getManifestation().getId());
			manifestationDto.setLabel(r.getManifestation().getLabel());
			manifestationDto.setDateDebut(r.getManifestation().getDateDebut());
			manifestationDto.setDateFin(r.getManifestation().getDateFin());
			manifestationDto.setPrixBillet(r.getManifestation().getPrixBillet());
			reservationDto.setManifestation(manifestationDto);
			
			AnimationDto animationDto = new AnimationDto();
			animationDto.setId(r.getManifestation().getAnimation().getId());
			animationDto.setLabel(r.getManifestation().getAnimation().getLabel());
			manifestationDto.setAnimation(animationDto);
			
			SalleDto salleDto = new SalleDto();
			salleDto.setId(r.getManifestation().getSalle().getId());
			salleDto.setLabel(r.getManifestation().getSalle().getLabel());
			manifestationDto.setSalle(salleDto);
			
			UserDto clientDto = new UserDto();
			clientDto.setId(r.getClient().getId());
			clientDto.setNom(r.getClient().getNom());
			reservationDto.setClient(clientDto);

			reservationDto.setNumClient(r.getNumClient());
			reservationDto.setQuantite(r.getQuantite());
			reservationDto.setDateReservation(r.getDateReservation());
			reservationDto.setTotal(r.getQuantite()*r.getManifestation().getPrixBillet());

			return reservationDto;
		}).collect(Collectors.toList());
	}
	
	@Override
	public ReservationDto findById(int id) {
		Optional<Reservation> reservationOp = this.reservationRepository.findById(id);
		ReservationDto reservationDto = new ReservationDto();
		if (reservationOp.isPresent()) {
			
			Reservation reservation = reservationOp.get();
			
			reservationDto.setId(reservation.getId());

			ManifestationDto manifestationDto = new ManifestationDto();
			manifestationDto.setId(reservation.getManifestation().getId());
			manifestationDto.setLabel(reservation.getManifestation().getLabel());
			reservationDto.setManifestation(manifestationDto);
			
			UserDto clientDto = new UserDto();
			clientDto.setId(reservation.getClient().getId());
			clientDto.setNom(reservation.getClient().getNom());
			reservationDto.setClient(clientDto);

			reservationDto.setNumClient(reservation.getNumClient());
			reservationDto.setQuantite(reservation.getQuantite());
			reservationDto.setDateReservation(reservation.getDateReservation());
			reservationDto.setTotal(reservation.getQuantite()*reservation.getManifestation().getPrixBillet());
			
		}
		return reservationDto;
		
	}
	
	@Override
	public List<ReservationDto> findReservationByUserId(int id) {

		List<ReservationDto> listReservationByClient =this.reservationRepository
				.findReservationByUser(id)
				.stream().map(r-> {
					
					ReservationDto reservationDto = new ReservationDto();

					reservationDto.setId(r.getId());

					ManifestationDto manifestationDto = new ManifestationDto();
					manifestationDto.setId(r.getManifestation().getId());
					manifestationDto.setLabel(r.getManifestation().getLabel());
					
					AnimationDto animationDto = new AnimationDto();
					animationDto.setId(r.getManifestation().getAnimation().getId());
					animationDto.setLabel(r.getManifestation().getAnimation().getLabel());
					manifestationDto.setAnimation(animationDto);
					
					manifestationDto.setPrixBillet(r.getManifestation().getPrixBillet());
					manifestationDto.setDateDebut(r.getManifestation().getDateDebut());
					manifestationDto.setDateFin(r.getManifestation().getDateFin());
					
					SalleDto salleDto = new SalleDto();
					salleDto.setId(r.getManifestation().getSalle().getId());
					salleDto.setLabel(r.getManifestation().getSalle().getLabel());
					manifestationDto.setSalle(salleDto);
					
					reservationDto.setManifestation(manifestationDto);
					
					UserDto clientDto = new UserDto();
					clientDto.setId(r.getClient().getId());
					clientDto.setNom(r.getClient().getNom());
					reservationDto.setClient(clientDto);

					reservationDto.setNumClient(r.getNumClient());
					reservationDto.setQuantite(r.getQuantite());
					reservationDto.setDateReservation(r.getDateReservation());
					reservationDto.setTotal(r.getQuantite()*r.getManifestation().getPrixBillet());

					return reservationDto;
				}).collect(Collectors.toList());
		
		return listReservationByClient;
	}

	@Override
	public List<ReservationDto> findReservationByManifestationId(int id) {

		List<ReservationDto> listReservationByManifestation =this.reservationRepository
				.findReservationByManifestation(id)
				.stream().map(r-> {
					
					ReservationDto reservationDto = new ReservationDto();

					reservationDto.setId(r.getId());

					ManifestationDto manifestationDto = new ManifestationDto();
					manifestationDto.setId(r.getManifestation().getId());
					manifestationDto.setLabel(r.getManifestation().getLabel());
					
					AnimationDto animationDto = new AnimationDto();
					animationDto.setId(r.getManifestation().getAnimation().getId());
					animationDto.setLabel(r.getManifestation().getAnimation().getLabel());
					manifestationDto.setAnimation(animationDto);
					
					manifestationDto.setPrixBillet(r.getManifestation().getPrixBillet());
					manifestationDto.setDateDebut(r.getManifestation().getDateDebut());
					manifestationDto.setDateFin(r.getManifestation().getDateFin());
					
					SalleDto salleDto = new SalleDto();
					salleDto.setId(r.getManifestation().getSalle().getId());
					salleDto.setLabel(r.getManifestation().getSalle().getLabel());
					manifestationDto.setSalle(salleDto);
					
					reservationDto.setManifestation(manifestationDto);

					UserDto clientDto = new UserDto();
					clientDto.setId(r.getClient().getId());
					clientDto.setNom(r.getClient().getNom());
					reservationDto.setClient(clientDto);

					reservationDto.setNumClient(r.getNumClient());
					reservationDto.setQuantite(r.getQuantite());
					reservationDto.setDateReservation(r.getDateReservation());
					reservationDto.setTotal(r.getQuantite()*r.getManifestation().getPrixBillet());

					return reservationDto;
				}).collect(Collectors.toList());

		return listReservationByManifestation;
	}
	
	@Override
	public int add(ReservationDto reservationDto) {
		int id=0;
		Optional <Reservation> reservationOp = this.reservationRepository
				.findReservationByUserAndManifestation(reservationDto.getClient().getId(), reservationDto.getManifestation().getId());
		if (!reservationOp.isPresent()) {
		
			Reservation reservation = new Reservation();
			
			Optional <Manifestation> manifestationOp = this.manifestationRepository.findById(reservationDto.getManifestation().getId());
			if (manifestationOp.isPresent()) {
				Manifestation manifestation = manifestationOp.get();
				reservation.setManifestation(manifestation);
			}

			
			Optional <User> clientOp = this.userRepository.findById(reservationDto.getClient().getId());
			if (clientOp.isPresent()) {
				User client = clientOp.get();
				reservation.setClient(client);
				reservation.setNumClient(client.getNom().substring(0,1)+client.getPrenom().substring(0,1)+"2020");
				
			}
			
			reservation.setQuantite(reservationDto.getQuantite());
			reservation.setDateReservation(reservationDto.getDateReservation());
			
			id=this.reservationRepository.save(reservation).getId();
			
			return id;
		}
		return id;
	}

	@Override
	public boolean update(ReservationDto reservationDto, int id) {
		
		Optional<Reservation> reservationOp = this.reservationRepository.findById(id);
		if (reservationOp.isPresent()) {
			Reservation reservation = reservationOp.get();
			
			Optional <Manifestation> manifestationOp = this.manifestationRepository.findById(reservationDto.getManifestation().getId());
			if (manifestationOp.isPresent()) {
				Manifestation manifestation = manifestationOp.get();
				reservation.setManifestation(manifestation);
			}

			Optional <User> clientOp = this.userRepository.findById(reservationDto.getClient().getId());
			if (clientOp.isPresent()) {
				User client = clientOp.get();
				reservation.setClient(client);
			}
			
			reservation.setNumClient(reservationDto.getClient().getNom().substring(0,1)+reservationDto.getClient().getPrenom().substring(0,1)+"2020");
			reservation.setQuantite(reservationDto.getQuantite());
			reservation.setDateReservation(reservationDto.getDateReservation());
			
			this.reservationRepository.save(reservation);
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean delete(int id) {
		if (this.reservationRepository.existsById(id)) {
			Optional<Reservation> reservationOp = this.reservationRepository.findById(id);
			if (reservationOp.isPresent()) {
				Reservation reservation = reservationOp.get();

				Optional <Manifestation> manifestationOp = this.manifestationRepository.findById(reservation.getManifestation().getId());
				if (manifestationOp.isPresent()) {
					Manifestation manifestation = manifestationOp.get();
					manifestation.setReservations(manifestation.getReservations()+reservation.getQuantite());
					this.manifestationRepository.save(manifestation);
				}
			}
			this.reservationRepository.deleteById(id);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.err.println("reservation supprimée");
			return true;
		}
		return false;
	}

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}