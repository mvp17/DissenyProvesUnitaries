package services;

import data.DigitalSignature;
import data.MailAddress;

public class MailerServiceImpl implements MailerService {

    public MailAddress address;

    @Override
    public void send(MailAddress address, DigitalSignature signature) {
        this.address = address;

    }
}
