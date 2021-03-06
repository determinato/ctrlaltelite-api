package com.charter.interpretme;

import com.charter.interpretme.repository.VolunteerProfileRepository;
import com.charter.interpretme.rest.entity.VolunteerProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for interacting with Volunteer entities.
 */
@RestController
@RequestMapping("/api/volunteers")
public class VolunteerProfileController {

    private VolunteerProfileRepository volunteerProfileRepository;

    @Autowired
    public VolunteerProfileController(VolunteerProfileRepository volunteerProfileRepository) {
        this.volunteerProfileRepository = volunteerProfileRepository;
    }

    @GetMapping
    public List<VolunteerProfile> getVolunteerProfiles() {
        return volunteerProfileRepository.findAll();
    }

    @GetMapping("/{profileId}")
    public VolunteerProfile findById(@PathVariable("profileId") String profileId) {
        return volunteerProfileRepository.findOne(profileId);
    }

    @PostMapping
    public VolunteerProfile saveVolunteer(@RequestBody VolunteerProfile profile) {
        return volunteerProfileRepository.save(profile);
    }

    @PutMapping("/{profileId}")
    public VolunteerProfile editVolunteer(@PathVariable("profileId") String profileId,
                                          @RequestBody VolunteerProfile profile) {
        return volunteerProfileRepository.save(new VolunteerProfile(profileId, profile.getUsername(), profile.getFirstName(),
                profile.getLastName(), profile.getLanguages(), profile.getStreetAddress1(), profile.getStreetAddress2(),
                profile.getCity(), profile.getState(), profile.getPostalCode(), profile.getEmailAddress(), profile.getPhoneNumber(),
                profile.getAverageRating(), profile.getPhotoLocation(), profile.getAge(), profile.getGender(),
                profile.getMeetInPerson(), profile.getContactMethod()));
    }

    @DeleteMapping("/{profileId}")
    public void deleteProfile(@PathVariable String profileId) {
        volunteerProfileRepository.delete(profileId);
    }

    @PostMapping("/user/{username}")
    public VolunteerProfile findByUsername(@PathVariable String username) {
        return volunteerProfileRepository.findByUsernameContainingIgnoreCase(username);
    }

    @PostMapping("/email/{email}")
    public VolunteerProfile findByEmail(@PathVariable("email") String emailAddress) {
        return volunteerProfileRepository.findByEmailAddressContainingIgnoreCase(emailAddress);
    }
}
